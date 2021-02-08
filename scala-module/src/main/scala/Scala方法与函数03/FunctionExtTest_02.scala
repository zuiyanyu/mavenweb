package Scala方法与函数03

/**
  * 函数式编程
  */
object FunctionExtTest_02 {
    def main(args: Array[String]): Unit = {


        //基本声明
        // TODO 1.如果使用return ，那么方法的返回值类型必须声明
        def f1():String  ={
            return "zhangsan" ;
        }
        //TODO 2. 函数如果明确了返回值类型为Unit，那么方法体中使用return不起作用。
        def f1_5():Unit = {
            return "中航三"
        }
        println ("f1_5() = "+f1_5());


        def f1_6():Unit = {
            "中航三"
        }
        println ("f1_6() = "+f1_6());


        //简化1  TODO 3. 如果不使用return ，那么方法的返回值类型可以省略 ，可以进行类型推断；取决与最后一行有效逻辑代码执行结果。
        def f1_1()= {
            "zhangsan"
        }
        println("f1_1()= " + f1_1())

        //简化2 ：TODO 4. 如果方法中没有声明返回值，并且也不需要返回值，同时也不希望进行类型推断，那么可以省略 = 号
        def f1_2() {
            "zhangsan"
        }
        println("f1_2()= " + f1_2())


        //TODO 5.如果函数声明时候是无参函数，并且方法名后面声明了(),那么调用的时候可省略()
        //TODO    但是如果函数声明时候方法名后面没声明()，那么调用的时候也必须不能带()，否则会异常。
        println("f1_2= " + f1_2)
        //无参无返回值，且方法名无()
        //TODO 这种形式 就和类声明的方式一样了，只是关键字是def 不是class而已
        def f1_2_1{
            "zhangsan"
        }
        //println("f1_2_1()= " + f1_2_1()) //编译时候报错
        println("f1_2_1= " + f1_2_1)

        //无参有返回值，且方法名无()
        def f1_2_2:String ={
            "zhangsan"
        }
        //println("f1_2_2()= " + f1_2_2()) //运行时候报错
        println("f1_2_2= " + f1_2_2)


        //TODO 简化3   只有一行逻辑体时候，可以省略 花括号
        def f1_3() = "zhangsan"
        println("f1_3()= " + f1_3())

        //TODO 简化4  必须加 = 以明确方法名和方法体；
        def f1_4 = "zhangsan"
        //println("f1_4()= " + f1_4())  //f1_4() 方式调用报错
        println("f1_4()= " + f1_4("zhangsan".length-1))  //f1_4() 正常执行
        println("f1_4= " + f1_4)  //可以调用

        //TODO 方法声明：可以看到此时和属性声明的形式一样  ，故：需要靠def 来区分成员变量和方法
        def f1_4_1 :String = "zhangsan"
        println("f1_4_1= " + f1_4_1 )  //可以调用

        //变量声明
        var f1_4_1_0 = "zhangsan"
        var f1_4_1_1:String = "zhangsan"

        //TODO 匿名函数  在不关心方法名的时候，可以使用lambda 表达式声明匿名函数
        //TODO  {} 和()都是代表表达式，只是（）的表达体只能有一个，而{}的表达体有一个或者多个 ，都会有返回值。
        var i = ()-> { var age = 10 ; "age = " + age}  //  tuple元组  () 和 {}的返回结果组成一个元组 ；({}是一个表达式)
        var i2 = ()-> (  "age  " )  //  tuple元组  () 和 {}的返回结果组成一个元组 ；({}是一个表达式)
        var j = "a" -> "A"       //  tuple元组
        var k = (age:Int)=>{ "zhangsan"}  //匿名函数
        println("i= " + i)  // i=  ((),age = 10)
        println("i2= " + i2)  // i=  ((),age  )
        println("j= " + j)  // j= (a,A)
        println("k= " + k)   // k= <function0>

//        println("i()= " + i())
        println("k()= " + k(20)) // k()= zhangsan




    }
}
