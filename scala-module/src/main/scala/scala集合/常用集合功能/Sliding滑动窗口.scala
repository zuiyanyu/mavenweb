package scala集合.常用集合功能

object Sliding滑动窗口 {
  def main(args: Array[String]): Unit = {
    //TODO 滑动窗口 window
    val list = List(1, 2, 3, 4, 5, 6)
    //参数1:窗口大小，  参数2：窗口滑动步长
    val iterator: Iterator[List[Int]] = list.sliding(3,1)
    for (elem <- iterator) {
      println(s"elem: $elem")
    }
//    结果
//    elem: List(1, 2, 3)
//    elem: List(2, 3, 4)
//    elem: List(3, 4, 5)
//    elem: List(4, 5, 6)
  }

}
