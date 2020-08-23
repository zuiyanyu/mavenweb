package javaVMJDK7.虚拟机执行子系统.动态类型;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;
/**
 *JSR-292 Method Handle
 *MethodHandle基本用途的演示
 *@author zzm
 */
public class MethodHandleTest{
    static class ClassA{
        public void println( String s) {
            System.out.println( s) ;
        }
    }

    /**
     * 1.此方法模拟了invokevirtual指令的执行过程，只不过它的分派逻辑并非固化在Class文件的字节码上，而是通过一个具体方法实现。
     * 2.返回值MethodHandle，可以视为对最终调用方法的一个"引用"。
     *
     * 3. 本质上讲，反射Reflection和MethodHandle机制都是在模拟方法调用。
     *    区别：
     *    1. Reflection是在模拟Java代码层次的方法调用，而MthodHandle是在模拟字节码层次的方法调用。
     *    2.在MethodHandles.lookup中的三个方法--findStatic()、findVirtual()、findSpecial()正是为了
     *     对应于invokestatic、invokevirtual&invodeinterface、invokespecial 这几条字节码指令的执行权限校验行为。
     *     而这些底层细节在使用Reflection API的时候是不需要关心的。
     *   3. Reflection是重量级，而MethodHandle是轻量级。
     *      Reflection中的java.lang.reflect.Method对象远比MethodHandle机制中的java.lang.invoke.MethodHandle对象包含的信息多。
     *      前者是方法在Java一端的全面映射，包含了方法的签名、描述符、方法属性表中各种属性的Java端表示方式、执行权限等
     *      后者仅仅包含与执行该方法相关的信息。
     *
     *   4.  Reflection API的设计目标只是Java语言服务的，而MethodHandle则设计成可服务于所有Java虚拟机之上的语言，其中也包括Java语言。
     * @param reveiver
     * @return
     * @throws Throwable
     */
    private static MethodHandle getPrintlnMH( Object reveiver) throws Throwable{
        //TODO 1.MethodType：代表"方法类型"，包含了方法的返回值（第一个参数） 和 具体参数（methodType（）的第二个以及以后的参数）
        // (方法的返回值类型，方法的入参类型)
         MethodType mt=MethodType.methodType( void.class,String.class) ;

         //TODO 2.因为这里调用的是一个虚方法，按照java语言的规则，方法第一个参数是隐士的，代表该方法的接收者，也即是this指向的对象。
         //TODO 这个参数以前是放在参数列表中进行传递的，而现在提供了bindTO()方法来完成这件事情，所以reveiver是this的指向对象。
         //(方法接受者类型，方法名，mt) .bindTo(方法接收者类型的实例)
        return lookup( ).findVirtual( reveiver.getClass( ) ,"println",mt) .bindTo( reveiver) ;
    }

    public  static void main( String[]args) throws Throwable{
        Object obj=System.currentTimeMillis( ) %2==0?System.out: new ClassA( ) ;

        //TODO 无论obj是何种类型，都可以调用到println()方法。
        getPrintlnMH(obj).invokeExact( "hello world") ;
        //TODO invokedynamic 指令：
        /**
         * 1.使用目的：
         * 在某种程度上，invokedynamic 指令与MethodHandle机制的作用是一样的，都是为了解决原有的4条“invoke*”指令方法分派规则固化在
         * 虚拟机之中的问题，把如何查找目标方法的决定权从虚拟机转嫁到具体用户代码中，让用户（包含其他语言的设计者）有更高的自由度。
         *
         * 而且，他们两者的思路也是可类比的，可以吧他们想象成为了达成同一个目的，一个采用上层代码和API来实现，另一个用字节码
         * 和Class中其他属性、常量来完成。
         *
         * 2. 实现原理：
         * 2.1 每一处含有invokedynamic指令的位置都称为 “动态调用点”（Dynamic Call Site）
         * 2.2 这条指令的第一个参数不再是代表方法符号引用的 CONSTANT_Method_info常量，
         *     而是变为JDK1.7新加入的CONSTANT_InvodeDynamic_info 常量。
         * 2.3 从CONSTANT_InvodeDynamic_info 常量中可以得到3项信息：
         *      a.引导方法（Bootstrap Method，此方法存放在新增的BootstrapMethods属性中）
         *      b.方法类型（MethodType ， 包含了方法的返回值类型和方法的入参类型）
         *      c.方法名称
         * 引导方法是固定的参数，并且返回值是java.lang.invoke.CallSite对象，这个代表要真正执行的目标方法调用。
         * 根据CONSTANT_InvokeDynamic_info常量中提供的信息，虚拟机可以找到并这行引导方法，从而获取一个CallSite对象，最终调用
         * 要执行的目标方法。
         *
         */
    }
}

































