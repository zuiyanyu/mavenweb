package javaVMJDK7.虚拟机执行子系统.类加载时机7_2;

/**
 * 非主动使用类字段
 */
public class ClassLoading {
    public static void main(String[] args) {
        // ====================被动引用实例2===============================
        // TODO 1. 对于静态字段，只有直接定义这个字段的类才会被初始化，因此通过此子类来引用父类中定义的静态字段，只会触发父类的初始化
        //TODO 而不会触发子类的初始化 ；    但是此操作会导致子类的加载。  -XX:+TraceClassLoading 可以跟踪到
        /* System.out.println(SubClass.value);*/

        // ====================被动引用实例2===============================
        //TODO 通过数组定义来引用类，不会触发此类的初始化
       /* SupperClass[] supperClasses = new SupperClass[10];*/
      /*  SubClass[]    subClasses = new SubClass[20];*/

        // ====================被动引用实例3===============================
        //TODO 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类。
        /*System.out.println(ConstClass.HELLO);*/


//        System.out.println(new SubClass().getInfo());

        System.out.println(ConstClass.school);

    }
}
// ================被动引用实例1===================================
interface CommonClass{
//     int field = 123 ;
     String getInfo();
}
/**
 * 被动使用字段
 * 通过子类引用父类的静态字段，不会导致子类初始化
 */
class SupperClass{
//    int field = 123 ;
    static {
        System.out.println("supperClass init ");
    }
    //没有加final,此属性定义在 Class类属性中 ，只要类初始化后才能被使用。
    public static int value = 123  ;
    public String getInfo(){
        return "hello";
    }

}
class SubClass extends SupperClass implements CommonClass{
    static {
        System.out.println("Subclass init");
    }
   // TODO SupperClass 没有实现接口类中的getInfo（）接口方法没报错，是因为SupperClass的父类SupperClass中有相同的方法。

}
// ====================被动引用实例2===============================
/* SupperClass[] supperClasses = new SupperClass[10];*/
// ====================被动引用实例3===============================

/**
 * 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类。
 */
class ConstClass{
    // TODO  1. <clinit>() 方法 是由编译器自动收集类中的所有的类变量的赋值动作 和 静态语句块（static{}）中的语句 合并产生的
    // TODO 编译器收集的顺序是由语句在源文件中出现的顺序所决定的。
    // TODO  2. 虚拟机会保证在子类的<clinit>()方法执行之前，父类的<clinit>()方法已经执行完毕。 因此第一个被执行的<clinit>()方法的类肯定是java.lang.Object.
    // TODO  3.由于父类的 <clinit>() 方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作。
    static int age ;
    static String name = "hello" ;
    static {
        System.out.println("ConstClass init ！");

        System.out.println("age="+age);
        age =20 ;
        System.out.println("age="+age);


        //TODO 静态语句块中只能访问到定义在静态语句块之前的变量，定在在它之后的变量，不能进行访问，但是可以
        //TODO 进行进行赋值。但是没生效。
        school = "20";  //给变量赋值可以正常编译通过，但是不会生效。
        //! System.out.println(school); // illegal forward reference
     }
    static String school = "school" ;
    //加final,此属性定义在调用类的常量池中，无需初始化类，直接从常量池中获取即可。
    public static final String HELLO = "Hello" ;
}
