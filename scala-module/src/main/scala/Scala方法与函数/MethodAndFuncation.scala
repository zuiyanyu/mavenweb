package Scala方法与函数



/**  函数：体现的是功能的封装(和对象无关) 类似于java中的static 方法
  *  方法：必须是属于某个对象的
  *
  *  为了区分方法和属性，使用def 关键字来声明方法。
  *
  * 1. Scala 有方法与函数，二者在语义上的区别很小.
  * 2. Scala 方法是类的一部分，而函数是一个对象 ,函数可以赋值给一个变量。   换句话来说在类中定义的函数即是方法。
  * 3. Scala 中的方法跟 Java 的类似，方法是组成类的一部分。Scala 中的函数则是一个完整的对象（Scala 中的函数其实就是继承了 Trait 的类的对象。）。
  *
  *  4. Scala 中使用 val 语句可以定义函数，def 语句定义方法。
  *  5.函数就是一个句柄/引用 。
  *
  *
  *  6. Scala 方法声明格式如下： def functionName ([参数列表]) : [return type]
  *  如果你不写等于号和方法主体，那么方法会被隐式声明为抽象(abstract)，包含它的类型于是也是一个抽象类型。
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
object MethodAndFuncation {
     def main(args: Array[String]): Unit ={
       val obj = new MethodAndFuncation

       //方法的使用
       val method = obj.getUserInfo("李四",11)
       println(method) //name = 李四 ; age = 11

       //函数的使用
       val func = obj.user("张三",11)
       println(func) //  name = 张三 ; age = 11


       //函数的使用  返回类型也是函数
       val user = obj.user
       println(user)   // <function2>
       println(user("张三2",12))

       //方法的使用   返回值后面有_ ,返回类型也是函数
       val info = obj.getUserInfo _
       println(info)   //<function2>
       println(info("李四2",11))

     }
}

class MethodAndFuncation{
  def getUserInfo(name : String ,age : Integer ): String ={
    " name = " + name + " ; age = " + age
  }
  val  user = (name : String ,age : Integer )  => {
    " name = " + name + " ; age = " + age
  }
}

class User{
  def getUserName(userName : String ): String  ={
     userName + " ========"
  }
}
