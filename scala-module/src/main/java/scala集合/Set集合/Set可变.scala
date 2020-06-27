package scala集合.Set集合

import scala.collection.mutable

object Set可变 {
  def main(args: Array[String]): Unit = {
    function
    add
    query
    define

  }
  //TODO 4.  功能介绍
  def function(): Unit ={
    println("=========TODO 4.  功能介绍===============")
    val set: mutable.Set[Int] = mutable.Set(1,2,3,3)

    //删除元素 的两种方式
    set -= 3 ;
    this.print_(set)  //1	2

    set.remove(2);
    this.print_(set)  //1

    //添加元素
    set += 2 ;
    set += 3 ;
    this.print_(set)  // 1	2	3

    //修改数据   true 添加数据，false 删除数据
    set.update(4,true)
    this.print_(set)  //1	2	3	4

    println("------------------------")
    //TODO 扁平化：将一个list集合中的元素添加到set集合中
    val list: List[Int] = List(5,6,7,8)
    set ++= list
    this.print_(set)  //1	5	2	6	3	7	4	8

    //head: 集合的第一个元素
    val head: Int = set.head
    println("head =" + head)  //1

    //tail: 集合的尾  ：除去头元素，剩余的都是尾元素
    val tail: mutable.Set[Int] = set.tail
    this.print_(tail)  // 5	2	6	3	7	4	8

    //last: 最后一个元素
    val last: Int = set.last
    println(s"last = $last")  // last = 8

    //init: 除了最后一个元素，剩余的所有元素
    val init: mutable.Set[Int] = set.init
    this.print_(init) //1	5	2	6	3	7	4
  }

  //TODO 3. 元素添加
  def add(): Unit ={
    println("==========TODO 3. 元素添加==============")
    val set: mutable.Set[Int] = mutable.Set(1,2,3,3)

    // 方式1   TODO +   ：无论是可变set ，还是不可变的Set，使用 + 添加元素都会产生新的Set
    val set2: mutable.Set[Int] = set + 5
    this.print_(set2)  //1	5	2	3
    this.print_(set)  // 1	2	3

    //方式2
    set += 5
    this.print_(set)  // 1	5	2	3

    //方式3
    set.add(6)
    this.print_(set)  // 1	5	2	6	3

  }
  //TODO 2.  遍历方式
  def query(): Unit ={
    println("=========TODO 2.  遍历方式===============")
    val set: mutable.Set[Int] = mutable.Set(1,2,3,3)

    for (elem <- set) {
      print(elem +"\t")
    };println;
  }
  //TODO 1.  声明方式
  def define(): Unit ={
    println("==========TODO 1.  声明方式==============")
    //方式1
    val set: mutable.Set[Int] = mutable.Set(1,2,3,3)
    this.print_(set)

  }
  def print_[T](seq:mutable.Set[T]): Unit ={
    println(seq.mkString("\t"))
  }
}
