package scala类和对象.偏函数

object 实例应用 {
    def main(args: Array[String]): Unit = {
        val list = List(1, 2, List(3, 4, 5),"hello")

        //flatMap也可以使用偏函数
        val result = list.flatMap {
            case x: Int => List(x)
            case y: Iterable[Int] =>y
            case z:Any =>  List(z)
        }
        println(result) // List(1, 2, 3, 4, 5, hello)


    }

}
