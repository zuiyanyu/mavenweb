package scala异常

/**
  * TODO 我们将可疑代码封装在try块中。 在try块之后使用了一个catch处理程序来捕获异常。如果发生任何异常，catch处理程序将处理它，程序将不会异常终止。
  * TODO Scala的异常的工作机制和Java一样，但是Scala没有“checked(编译期或受检)”异常，即Scala没有编译异常这个概念，异常都是在运行的时候捕获处理。
  */
object TryCatchTest {
    def main(args: Array[String]): Unit = {
        try{
            f()
            throwException();
//           val r =  1/0
        }catch {
            //TODO 模式匹配来获取异常  按照顺序捕获异常
            //TODO 异常的顺序随意放，不会报错； 如果范围大的在前，那么返回小的就不能被使用到了。
            case n :NumberFormatException => println("NumberFormatException："+n.getMessage)
            case e :Exception => println("Exception : "+e.getMessage)
            case m :ArithmeticException => println("ArithmeticException:"+ m.getMessage());
        }finally{
            println("finally 。。。")
        }
    }
    //TODO 方式1 ： 显示排除异常
    def throwException(): Nothing ={
        throw new Exception("抛出一个异常");
    }
    //TODO 方式2 ：注解方式声明可能会抛出的异常
    /**
      * 用throw关键字，抛出一个异常对象。
      * 所有异常都是Throwable的子类型。
      * throw表达式是有类型的，就是Nothing，因为Nothing是所有类型的子类型，所以throw表达式可以用在需要类型的地方
      *
      * @throws NumberFormatException
      */
    @throws[NumberFormatException]
    def f(): Unit ={
        "abc".toInt
    }

}
