package scala集合.常用集合功能

object Zip拉链 {
  def main(args: Array[String]): Unit = {
    // TODO 对偶元组 组合
      val list1 = List(1,2,3,4,5,6,7)
      val list2 = List("a", "b", "c", "d", "e")

      val zipRes: List[(Int, String)] = list1.zip(list2)

      println(zipRes)  // List((1,a), (2,b), (3,c), (4,d), (5,e))

  }

}
