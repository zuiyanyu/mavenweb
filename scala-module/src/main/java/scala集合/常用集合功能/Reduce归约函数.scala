package scala集合.常用集合功能

import scala.collection.mutable.ListBuffer

object Reduce归约函数 {
  def main(args: Array[String]): Unit = {
      //TODO 归约 即化简 函数  reduce
      val list6: ListBuffer[Int] = ListBuffer(1,2,3,4)

      //TODO 折叠/聚合函数：从左往右，相邻的两个数据做聚合  入参：op: (A1, A1) => A1
      val reduceRest: Int = list6.reduce((l, r) => l + r)  // list6.reduce( _+_)
      println(reduceRest);  // 10

      //TODO 折叠/聚合函数： 和 reduce 的作用一样 reduce 底层就是调用reduceLeft
      println(list6.reduceLeft(_+_))  //10
      println(list6.reduceLeft(_-_)) //-8


      //TODO 折叠/聚合函数：从右往左，相邻的两个数据做聚合
      println(list6.reduceRight(_+_))//10
      println(list6.reduceRight(_-_))//-2  = (1-(2-(3-4)) )
  }

}
