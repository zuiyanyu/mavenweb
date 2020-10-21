package scala类和对象.密封类

/**
  * 基本介绍  这是一种规范，方式出错
  * 1. 如果想让case类的所有子类都必须在申明该类的相同的源文件中定义，可以将样例类的通用超类声明为sealed，
  *    这个超类称之为密封类。
  * 2. 密封就是不能在其他文件中定义子类。
  */
object Sealed密封类 {
    def main(args: Array[String]): Unit = {

    }

}
abstract sealed class Amount
case class Dollar(value: Double) extends Amount
case class Currency(value: Double, unit: String) extends Amount
case object NoAmount extends Amount
