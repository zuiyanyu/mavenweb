package scala类和对象04.样例类

object 样例类的copy方法02 {
    def main(args: Array[String]): Unit = {
        //TODO 2. 样例类的copy方法和带名参数
        //TODO copy方法作用：创建一个与现有对象值相同的新对象 ，并可以通过带名参数来修改某些属性。
        val amt = Currency(29.95, "RMB")

        val amt1 = amt.copy()   //TODO 这里只是单纯的复制了引用地址
        val amt2 = amt.copy(value = 19.95)
        val amt3 = amt.copy(unit = "英镑")

        println(amt)  // Currency(29.95,RMB)
        println(amt.equals(amt1)) //true
        amt.value=200.2 ;
        println(amt)  //Currency(200.2,RMB)

        println(amt1)  // Currency(29.95,RMB)
        println(amt2)  // Currency(19.95,RMB)
        println(amt3)  //Currency(29.95,英镑)

    }
    //TODO  1. 定义一个样例类  变量默认为val类型
    case class Currency(var value: Double, unit: String);

}
