package a基础语法

import scala.io.StdIn

object OperTest {
    def main(args : Array[String]): Unit ={
        var i:Int = 20 ;
        println(i = 3);  // ()

        var a = 2.7 ;
        println(a == 8.1 / 3 )

        var b = 27 ;
        println(b == 81 / 3 )


        val r = if(1==1) a else b ;
        println(r);

        println("请输入你的年龄：");
        val age = StdIn.readLine()
        println(s"age = ${age}" );

        val inclusive: Range = 1 to 3
        val range:Range = Range(1,3)

        val inclusive2 :Range.Inclusive = Range.inclusive(1,3,1)

        for (i <- 1 to 4){
            println(i)
        }
    }
}
