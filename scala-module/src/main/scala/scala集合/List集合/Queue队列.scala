package scala集合.List集合

import scala.collection.mutable

/**
  * 对列 queue ：对列一般是可变的，元素要不断进出。
  * 队列：先进先出
  */
object Queue队列 {
  def main(args: Array[String]): Unit = {
//    function
    add
//    query()
//    define
  }
  //TODO 4.  功能介绍
  def function(): Unit ={
    println("=========TODO 4.  功能介绍===============")
    val queue: mutable.Queue[Int] = mutable.Queue[Int](1,2,3)
    printQueue(queue)
  }

  //TODO 3. 元素添加
  def add(): Unit ={
    println("==========TODO 3. 元素添加==============")
    val queue: mutable.Queue[Int] = new mutable.Queue[Int]
    //方式1
    queue += 1
    queue += 2
    this.printQueue(queue) // 1	2

    val head: Int = queue.head
    println("head = " + head)//head = 1 先进先出
    this.printQueue(queue) // 1	2

    //方式2
    queue.enqueue(3);
    queue.enqueue(4,5,6);
    this.printQueue(queue) // 1	 2	3	4	5	6


  }
  //TODO 2.  遍历方式
  def query(): Unit ={
    println("=========TODO 2.  遍历方式===============")
    val queue: mutable.Queue[Int] = mutable.Queue[Int](1,2,3)  // queue = 3,2,1

    //弹栈  获取队列中的第一个元素(先进的元素)
    queue.dequeue()
    this.printQueue(queue) //2	3


  }
  //TODO 1.  声明方式
  def define(): Unit ={
    println("==========TODO 1.  声明方式==============")
    //对列   先进先出
    val queue: mutable.Queue[Int] = new mutable.Queue[Int]()

  }
  def printQueue[T](queue:mutable.Queue[T]): Unit ={
    println(queue.mkString("\t"))
  }

}
