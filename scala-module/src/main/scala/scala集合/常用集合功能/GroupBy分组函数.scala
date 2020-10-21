package scala集合.常用集合功能

import scala.collection.mutable.ListBuffer

object GroupBy分组函数 {
  def main(args: Array[String]): Unit = {
    val list2: ListBuffer[Int] = ListBuffer(4,5,6,16,14,13)

    //TODO 对元素进行分组  （比如按照元素的奇偶进行分组）
    val groupBy: Map[String, ListBuffer[Int]] = list2.groupBy(x=>{if(x%2==1) "奇数" else "偶数"})
    println(groupBy) // Map(奇数 -> ListBuffer(5, 13), 偶数 -> ListBuffer(4, 6, 16, 14))
  }

}
