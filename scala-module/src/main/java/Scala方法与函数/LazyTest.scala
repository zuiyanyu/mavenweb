package Scala方法与函数

object LazyTest {
    def main(args: Array[String]): Unit = {
           val result = sum(10,20);
           println("----------------------")
           println("result="+ result);

        println("===========================")

        lazy val result2 = sum(10,20);
        println("----------------------")
        println("result2="+ result2);


    }
    def sum(a:Int ,b :Int):Int ={
        println("comming into layzTest() ... ")
        return a + b ;
    }
}
