package Scala方法与函数

/**
  * 函数式编程
  */
object FunctionExtTest {
    def main(args: Array[String]): Unit = {

        //基本声明    如果不使用return ，那么方法的返回值类型必须声明
        def f1():String  ={
            return "zhangsan" ;
        }
        //简化1   如果不使用return ，那么方法的返回值类型可以省略 ，可以进行类型推断；取决与最后一行有效逻辑代码执行结果。
        def f1_1()= {
            "zhangsan"
        }
        println("f1_1()= " + f1_1())

        //简化2 ：     如果方法中没有声明返回值，并且也不需要返回值，同时也不希望进行类型推断，那么可以省略 = 号
        def f1_2() {
            "zhangsan"
        }
        println("f1_2()= " + f1_2())
        def f1_2_1 {
            "zhangsan"
        }
        println("f1_2_1()= " + f1_2_1)

        //TODO 简化3   已有一行逻辑体时候，可以省略 花括号
        def f1_3() = "zhangsan"
        println("f1_3()= " + f1_3())

        //TODO 简化4  必须加 = 以明确方法名和方法体；
        def f1_4 = "zhangsan"
//        println("f1_4()= " + f1_4())  //f1_4() 方式调用报错
        println("f1_4= " + f1_4)  //可以调用

        //TODO 方法声明：可以看到此时和属性声明的形式一样  ，故：需要靠def 来区分成员变量和方法
        def f1_4_1 :String = "zhangsan"
        println("f1_4_1= " + f1_4_1 )  //可以调用

        //变量声明
        var f1_4_1_0 = "zhangsan"
        var f1_4_1_1:String = "zhangsan"

        //TODO 匿名函数  在不关心方法名的时候，可以使用lambda 表达式声明匿名函数
        var i = ()->{ "zhangsan"}  //匿名类
        var k = (age:Int)=>{ "zhangsan"}  //匿名函数
        println("i= " + i)  // i= ((),zhangsan)
        println("k= " + k)   // k= <function0>

//        println("i()= " + i())
        println("k()= " + k(20)) // k()= zhangsan


        //扩展1：函数如果明确了返回值类型为Unit，那么方法体中使用return不起作用。
        def f1_5():Unit = {
            return "中航三"
        }
        println ("f1_5() = "+f1_5());

        def f1_6():Unit = {
            "中航三"
        }
        println ("f1_6() = "+f1_6());

    }
}
