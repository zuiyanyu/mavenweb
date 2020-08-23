package javaVMJDK7.虚拟机执行子系统.动态类型;

import java.lang.invoke.*;

/**1. 动态语言和静态语言的区别：
 *    1.1 动态语言：变量无类型,而变量值才有类型。
 *    比如：编译时候最多只能确定方法名、参数、返回值这些信息，而不会去确定方法所在的具体类型(即方法接收者不固定)
 *    1.2 静态语言：变量有类型，变量值必须和变量类型保持一致。
 *    比如：编译时候除了能确定方法名、参数、返回值这些信息，还能确定方法所在的具体类型。
 *
 * 2. "变量无类型,而变量值才有类型" 的这个特点也是动态类型语言的一个重要特征。
 * 3.jdk 1.7 以前的字节码指令集中，4条方法调用指令（invokevirtual、invokespecial、invokestatic、invokeinterface）的第一个
 * 参数都是被调用的方法的符号引用。 并且，方法的符号引用在编译期产生。
 * 4. 因为动态语言只有在运行期才能确定方法接收者类型，所以在java虚拟机上实现的动态类型语言就不得不使用其他方式来实现。
 * 为了在虚拟机层次上提供动态类型的直接支持，才有了java.lang.invoke包 ，以及jdk1.7(JSR-292) 提供的invokedynamic指令。
 *
 * 5. java.lang.invoke包。
 *   5.1 此包是 JSR-292的一个重要组成部分。
 *   这个包的主要目的是在之前单纯依靠符号引用来确定调用的目标方法这种方式以外，提供一种新的动态确定目标方法的机制，
 *   称为 MethodHandle 。
 *   5.2 MethodHandle 可以视为对最终调用方法的一个“引用”。 只包含与执行方法相关的信息。
 * 6.  invokedynamic
 *   6.1 每一处含有invokedynamic 指令的位置都称为 “动态调用点”(Dynamic Call Site) ，这条指令的第一个参数是 jdk1.7新加入
 *   的CONSTANT_InvokeDynamic_info常量。
 *   6.2 从CONSTANT_InvokeDynamic_info常量可以得到3项信息：引导方法(Bootstrap) ，目标方法名，目标方法类型。
 *   6.3 引导方法是有固定的参数，并且返回值是 java.lang.invoke.CallSite对象，这个代表真正要执行的目标方法调用。
 *   6.4 虚拟机可以找到并且执行引导方法，从而获得一个CallSite对象，最终调用要执行的目标方法。
 *        这个BoottrapMethod（）方法java源码中没有，只能看它的字节码。
 *   6.5 invokedynamic执行过程：
 *   调用MethodHandles$Lookup的findStatic（）方法，产生目标方法的的MethodHandle，然后用它创建一个ConstantCallSite对象，
 *   最后这个对象返回给invokeddynamic指令实现对 目标方法(testMethod())的调用，invokeddynamic指令的调用过程就完成了。
 *
 *
 *   下面是一个实际例子来模拟解释这个过程。
 *
 */
import static java.lang.invoke.MethodHandles.lookup;
public class InvokeDynamicTest{
    public static void main( String[]args) throws Throwable{
        //4.精准执行目标方法
        INDY_BootstrapMethod().invokeExact( "icyfenix") ;
    }

    //目标方法
    public static void testMethod( String s) {
        System.out.println( "hello String！ "+s) ;
    }

    /**
     * 引导方法 (获取执行目标方法的调用点)
     * 引导方法是有固定的参数，并且返回值是 java.lang.invoke.CallSite对象，这个代表真正要执行的目标方法调用。
     * @param lookup
     * @param name
     * @param mt
     * @return
     * @throws Throwable
     */
    public static CallSite BootstrapMethod( MethodHandles.Lookup lookup,String name,MethodType mt) throws Throwable{
        // ConstantCallSite(MethodHandle target)   封装了 目标方法的句柄MethodHandle到 调用点中。
        // 最终还是执行目标方法句柄的 invokeExact()方法。
        // 静态方法不需要 bindTo 绑定到某个类的实例上。
        return new ConstantCallSite( lookup.findStatic( InvokeDynamicTest.class,name,mt) ) ;
    }

    //1. 方获取引导方法的类型
    private static MethodType MT_BootstrapMethod( ) {
        //以下两种返回MethodType的方式，都能执行出正确结果
        //因为底层还是会将  Ljava/lang/String变为 java.lang.String的形式，然后使用 ClassLoader.loadClass（java.lang.String）进行加载。

//        return MethodType.methodType(CallSite.class,MethodHandles.Lookup.class,String.class,MethodType.class) ;
        return MethodType
                .fromMethodDescriptorString(
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;".replaceAll("\\s+",""),
                        null) ;
    }
    //2. 获取引导方法的句柄
    private static MethodHandle MH_BootstrapMethod( ) throws Throwable{

        return lookup( ) .findStatic( InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod( ) ) ;
    }
    //3. 获取目标方法的句柄
    private static MethodHandle INDY_BootstrapMethod( ) throws Throwable{

        //引导方法(Bootstrap) 返回CallSite 对象，
        CallSite cs=( CallSite) MH_BootstrapMethod( )
                .invokeWithArguments(   lookup( )
                                        , "testMethod"
                                        , MethodType.fromMethodDescriptorString( "(Ljava/lang/String;)V".replaceAll("\\s+",""), null)
                                     ) ;
        //返回目标方法的句柄
        return cs.dynamicInvoker( ) ;
    }}