package scala流程控制.scala_for

//import scala.util.control.Breaks


/**
  * Scala内置控制结构特地去掉了break和continue，是为了更好的适应函数化编程，
  * 推荐使用函数式的风格解决break和contine的功能，而不是一个关键字
  */
object BreakTest {
    def main(args: Array[String]): Unit = {
        break_test02
    }

    /**
      * scala中没有 break关键字，需要使用 Breaks.breakable 来模仿java中的break效果。
      */
    def break_test01(): Unit = {
        import scala.util.control.Breaks
        Breaks.breakable {
            for (i <- 1 to 10) {
                if (i == 8) {
                    Breaks.break();
                }
                print(s"i = ${i} \t  ");
            }
        }
    }

    /**
      * 使用  ._  进行导包，进一步模仿 java中的break样式
      */
    def break_test02(): Unit ={
        import scala.util.control.Breaks._
        //TODO scala 中的break用法
        breakable{
            for(i <- 1 to 10){
                if(i ==8){
                    break ;
                }
                print(s"i1 = ${i} \t  ");
            }
        };println;
    }


}
