package scala集合.常用集合功能

import scala.collection.immutable

object scanLeft扫描 {
  def main(args: Array[String]): Unit = {
    //TODO 扫描：即对集合中的所有元素进行fold，但是会把产生的所有的中间结果放置于一个集合中保存
    val range:List[Int] =List(1,2,3,4,5)

//    val ints: List[Int] = range.scan(0)(_+_)
    val scanRest: immutable.List[Int] = range.scan(0)(_+_)  //运算顺序；((((0+1+2)+3)+4)+5)
    println(scanRest) //List(0, 1, 3, 6, 10, 15)

    val scanLeftRest: immutable.List[Int] = range.scanLeft(0)((init,elem)=>{init+elem}) //运算顺序；((((0+1+2)+3)+4)+5)
    println(scanLeftRest) // List(0, 1, 3, 6, 10, 15)

    //运算从左向右两两运算的，中间结果也是从左往右存的。
    val scanRight: immutable.List[Int] = range.scanRight(0)(_+_)  //运算顺序：(1+(2+(3+(4+(5+0)))))
    println(scanRight) // List(15, 14, 12, 9, 5, 0)
  }

}
