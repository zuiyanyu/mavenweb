package scala_for循环

//import scala.util.control.Breaks
import scala.util.control.Breaks._

object BreakTest {
    def main(args: Array[String]): Unit = {


//       Breaks.breakable{
//           for(i <- 1 to 10 ){
//               if(i ==8){
//                   Breaks.break ;
//               }
//               print(s"i = ${i} \t  ");
//           }
//       }
//    }
        //TODO scala 中的break用法
        breakable{
            for(i <- 1 to 10 ){
                if(i ==8){
                    break ;
                }
                print(s"i1 = ${i} \t  ");
            }
        }
    } ;println;
    // TODO scala 可以在一个表达式式中使用多行语句，为了理解方便，将小括号便成花括号。
    //TODO  for循环也有返回值，默认返回值为 Unit 即（）
    // TODO 将遍历过程中处理的结果返回到一个新的Vector集合中，使用yield关键字

   var result = for{
                i <- 1 to 10
                j = i*2
                k = j+1
                if(k<20)
            }yield  k
    println(s"result=${result}");  // result=Vector(3, 5, 7, 9, 11, 13, 15, 17, 19)

    var result2 = for{
        i <- 1 to 10
        j = i*2
        k = j+1
    }yield {
        if(k<20)  k
    }
    println(s"result2=${result2}");  //  result2=Vector(3, 5, 7, 9, 11, 13, 15, 17, 19, ())


    println("============");

}
