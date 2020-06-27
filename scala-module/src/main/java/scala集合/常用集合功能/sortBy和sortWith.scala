package scala集合.常用集合功能

import scala.collection.mutable.ListBuffer

object sortBy和sortWith {
  def main(args: Array[String]): Unit = {
    val list2: ListBuffer[Int] = ListBuffer(4,5,6,16,14,13)

    //TODO 自定义规则进行排序所需要的key
    //TODO (默认按照字典顺序比较、或者数值大小比较结果，来确定排序的方式:升序或者降序)
    val sortBy: ListBuffer[Int] = list2.sortBy((elem) => {
      val i: Int = elem % list2.size
      i
    })
    println(sortBy) //ListBuffer(6, 13, 14, 4, 16, 5)

    // TODO 自定义两个key值比较方式，比较结果来决定排序的方式： 升序 或者 降序 。
    // TODO (改变了默认的排序方式：按照字典顺序比较、或者数值大小比较结果，来确定排序的方式:升序或者降序)
    val ints: ListBuffer[Int] = list2.sortWith((left, right) => {
      //左边 大于 右边 ，是降序排序
      left > right   //if(left > right) true; else  false ;
    })
    println(ints); //ListBuffer(16, 14, 13, 6, 5, 4)
  }

}
