package scala异常

object TryCatchTest {
    def main(args: Array[String]): Unit = {
        try{
            f()
//           val r =  1/0
        }catch {
            case n :NumberFormatException => println("NumberFormatException："+n.getMessage)
            case m :ArithmeticException => println("ArithmeticException:"+ m.getMessage());
            case e :Exception => println("Exception : "+e.getMessage)
        }finally{
            println("finally 。。。")
        }
    }
    @throws[NumberFormatException]
    def f(): Unit ={
        "abc".toInt
    }

}
