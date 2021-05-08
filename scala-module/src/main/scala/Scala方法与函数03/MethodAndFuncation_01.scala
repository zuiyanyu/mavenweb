package Scala方法与函数03

import java.lang.reflect.AnnotatedType


/**
  * Scala的命名规范：
  *   1.  "$"开头的标识符为保留的 Scala 编译器产生的标志符使用，应用程序应该避免使用"$"开始的标识符，以免造成冲突。
  *   2.  该避免使用以下划线 _ 结尾的标志符以避免冲突.
  *   3.  Scala 的命名规则采用和 Java 类似的 camel 命名规则，首字符小写
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
/**  函数：体现的是功能的封装(和对象无关) 类似于java中的static 方法
  *  方法：必须是属于某个对象的
  *
  *  为了区分方法和属性，使用def 关键字来声明方法。
  *
  * 1. Scala 有方法与函数，二者在语义上的区别很小.
  * 2. Scala 方法是类的一部分，而函数是一个对象 ,函数可以赋值给一个变量。   换句话来说在类中定义的函数即是方法。
  * 3. Scala 中的方法跟 Java 的类似，方法是组成类的一部分。Scala 中的函数则是一个完整的对象（Scala 中的函数其实就是继承了 Trait 的类的对象。）。
  *
  *  4.Scala 中使用def 定义函数 。
  *  5.函数就是一个句柄/引用 。
  *
  *
  *  6. Scala 方法声明格式如下： def functionName ([参数列表]) : [return type]={}
  *  TODO 如果你不写等于号和方法主体，那么方法会被隐式声明为抽象(abstract)，包含它的类型于是也是一个抽象类型。
  *
  *
  * 方法调用
  * Scala 提供了多种不同的方法调用方式：
  *
  * 以下是调用方法的标准格式：
  *
  * functionName( 参数列表 )
  * 如果方法使用了实例的对象来调用，我们可以使用类似java的格式 (使用 . 号)：
  *
  * [instance.]functionName( 参数列表 )
  *
  */
// scala是完全面向对象和完全面向函数的编程语言
object MethodAndFuncation {
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
     def  main(args: Array[String]): Unit ={
       val obj = new MethodAndFuncation

       //方法的使用
       val method = obj.getUserInfo("李四",11)
       println(method) //name = 李四 ; age = 11

       //函数的使用 直接调用了 obj的user函数，返回函数的执行结果。
       val func:String = obj.user("张三",11)
       println(func) //  name = 张三 ; age = 11


       //函数的使用  返回类型也是函数
       val user: (String, Integer) => String = obj.user
       println(user)   // <function2>
       println(user("张三2",12))

       //方法的使用   返回值后面有_ ,返回类型也是函数
       val info = obj.getUserInfo _
       println(info)   //<function2>
       println(info("李四2",11))

     }
}

class MethodAndFuncation{
  //声明一个函数，因为在类中声明，所以也是一个方法。
  def getUserInfo(name : String ,age : Integer ): String ={
    " name = " + name + " ; age = " + age
  }
  //使用匿名函数为 变量user 赋值
  val  user = (name : String ,age : Integer )  => {
    " name = " + name + " ; age = " + age
  }
}

class User{
  def getUserName(userName : String ): String  ={
     userName + " ========"
  }
  //定义私有方法
  private def getUserName2(userName : String ): String  ={
    userName + " ========"
  }
}
