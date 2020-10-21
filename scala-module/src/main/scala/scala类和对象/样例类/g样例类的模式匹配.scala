package scala类和对象.样例类

object g样例类的模式匹配 {
    def main(args: Array[String]): Unit = {
        exam09
    }

    //TODO 9.样例类的模式匹配
    /**
      * 1. 样例类用case关键字进行声明。
      * 2. 样例类是为模式匹配(对象)而优化的类
      * 3. 在样例类对应的伴生对象中提供apply方法让你不用new关键字就能构造出相应的对象
      * 4. 提供unapply方法让模式匹配可以工作
      *
      * 当我们有一个类型为Amount的对象时，可以用模式匹配来匹配他的类型，
      * 并将属性值绑定到变量(即：把样例类对象的属性值提取到某个变量)
      */
    def exam09(): Unit ={
        abstract class Amount
        case class Dollar(value: Double) extends Amount
        case class Currency(value: Double, unit: String) extends Amount
        case object NoAmount extends Amount

        val array: Array[Amount] = Array(
            Dollar(1000.0),  // Dollar(1000.0): Dollar(v) => v = 1000.0
            Currency(1000.0, "RMB"),  // Currency(1000.0,RMB): Currency(v, u) => v=1000.0,u=RMB
            NoAmount // NoAmount: NoAmount => 无参样例类
        )

        for (amt <- array) {
            val result = amt match {
                case Dollar(v) => s"Dollar(v) => v = $v"
                case Currency(v, u) => s"Currency(v, u) => v=$v,u=$u"
                case NoAmount => "NoAmount => 无参样例类"
                case _ => "没有匹配上"
            }
            println(amt + ": " + result)
        }

    }

}
