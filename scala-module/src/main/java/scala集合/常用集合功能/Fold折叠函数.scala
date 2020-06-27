package scala集合.常用集合功能

import scala.collection.mutable.ListBuffer

object Fold折叠函数 {
  def main(args: Array[String]): Unit = {
    //TODO fold折叠函数:将上一步返回的值作为函数的第一个参数继续参数运算，直到list中的所有元素被遍历
     //TODO foldLeft 缩写方法： "/:"    ; foldRight的缩写方法： ":\"
     //TODO 冒号 是和集合紧挨着的。
    val list6: ListBuffer[Int] = ListBuffer(1,2,3,4)

    //TODO 折叠/聚合函数：从左往右，初始化的元素和未计算过的相邻元素 (两者类型必须相同，且计算结果也必须相同)做运算进行聚合。
    // def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 = foldLeft(z)(op)
    println(list6.fold(0)(_ + _)) // 10
    println(list6.fold(1)(_ + _)) // 11

    //TODO 折叠/聚合函数：从左往右，初始化的元素和未计算过的相邻元素 (两者类型可相同，但是计算结果必须和初始化值类型相同)做运算进行聚合。
    //def foldLeft[B](z: B)(op: (B, A) => B): B = underlying.foldLeft(z)(op)
    println(list6.foldLeft(0)(_ + _)) //   （（（（0+ 1）+ 2）+3+4）=10
    println(list6.foldLeft(0)(_ - _))  //  （（（（0- 1）- 2）-3-4） = -10

    println(list6.foldRight(0)(_ - _))//     (1-(2-(3-(4-0)))) = -2
  }

}
