package Scala方法与函数

/**
  * Scala的命名规范：
  *   1. "$"开头的标识符为保留的 Scala 编译器产生的标志符使用，应用程序应该避免使用"$"开始的标识符，以免造成冲突。
  *   2.  该避免使用以下划线 _ 结尾的标志符以避免冲突.
  *   3. Scala 的命名规则采用和 Java 类似的 camel 命名规则，首字符小写
  *
  * 分割符：
  *   1. Scala 程序里,一行语句末尾的分号通常是可选的，可不写。如果一行里写多个语句那么分号是需要的
  */
/**
  *
  * object TestJava { // private，default，protected, public
  * // private : 同类
  * // default : 同类，同包
  * // protected : 同类，同包，子类
  * // public : 公共
  * def main(args: Array[String]): Unit =  { System.out.println("Hello, Java")
  * }
  * }
  *
  */
// scala是完全面向对象和完全面向函数的编程语言
object MainDefine_01 {
    // scala程序的入口依然是main方法
    // scala中声明方法或函数，需要使用def关键字
    // scala中声明的方法默认的访问权限就是public，所以可以省略
    // scala是完全面向对象的语言，所以没有静态语法，没有static关键字
    //      scala采用特殊的方法模拟静态访问功能
    //      scala编译类的时候会产生2个类文件，一个是原始类文件，还有一个伴生对象类文件(内部类文件)
    //      如果想要实现静态功能，就必须将静态功能放置在object（伴生对象）中
    // scala采用Unit对象代替void
    // scala中方法的参数，类型放置在后声明，而java放置在前声明
    // scala中用中括号表示泛型
    def main(args: Array[String]): Unit = {
        println("Hello, Scala");
    }
}
