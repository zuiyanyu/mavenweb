package scala变量01

/**
  * Scala变量使用说明:  var | val 变量名 [: 变量类型] = 变量值
  *
  * 1) 声明变量时，类型可以省略（就是叫 类型推断）
  * 2) 类型确定后，就不能修改，说明Scala 是强数据类型语言.
  * 3) 在声明/定义一个变量时，可以使用var 或者 val 来修饰， var 修饰的变量可改变，val 修饰的变量不可改
  * 4) val修饰的变量在编译后，等同于加上final， 通过反编译看下底层代码。
  *
  *  Scala中使用var,val声明变量:
  *     var关键字声明的变量的值可以改变
  *     val关键字声明的变量的值无法改变，类似于Java中的常量  public static final String NUM = "123"
  *
  */
object Scala变量01 {
    val age = 20

    def main(args: Array[String]): Unit = {



    }
    def float_double()={
        // 默认情况是double类型，float要加 f
        var a = 22.3

        var b = 33.3f ;

    }
    def int_long(): Unit ={
        // Scala的整型 常量/字面量  默认为 Int 型，声明Long型 常量/字面量 须后加‘l’’或‘L’
        var age = 20
        var money = 20L
    }

    /**
      * 1. 基础变量的声明 规则
      */
    def var01(): Unit ={
        //1. var：可变变量声明  引用地址可变
        var s1:String = "ABC"
        var s2  = "ABC"  //类型可省略，会自动推断，类型一旦确定就不能改变
        s1 = "abcd"


        //2. val:不可变变量声明  引用地址不可变
        // 这里定义的是 方法的局部变量，并不是类的成员变量，编译后不会加上final
        val s3:String = "ABC"
        //s3 = "abcd"  //编译报错

        //3. 成员变量使用val声明后，编译后会对变量加final修饰，说明变量的引用不能改变了， 但是引用的对象内容可改变
        val name = "zhangsan"
        val user = new User
        user.name = "lisi"

    }
}

class User{
    var name = "zhangsan"
    //成员变量使用val声明后，编译后会对变量加final修饰
    val age = 20 ;

}