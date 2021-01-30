package scala类和对象04.样例类

/**
  * 1. 可以这样理解样例类，就是样例类会默认其它很多的方法，供程序员直接使用
  * 2. 样例类仍然是类
  * 3. 样例类用case关键字进行声明。
  * 4. 样例类是为模式匹配(对象)而优化的类
  * 5. 构造器中的每一个参数都成为val——除非它被显式地声明为var
  * 6. 在样例类对应的伴生对象中提供apply方法让你不用new关键字就能构造出相应的对象
  * 7. 提供unapply方法让模式匹配可以工作
  * 8. 将自动生成toString、equals、hashCode和copy方法(有点类似模板类，直接给生成，供程序员使用)
  *9.  除上述外，样例类和其他类完全一样。你可以添加方法和字段，扩展它们
  */
object CaseClass样例类 {
    def main(args: Array[String]): Unit = {

    }
    abstract class Amount
    //  这里的 Dollar，Currencry, NoAmount  是样例类。
    case class Dollar(value: Double) extends Amount
    case class Currency(value: Double, unit: String) extends Amount
    case object NoAmount extends Amount
}

