package scala集合.集合的线程安全

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq

/*
   *TOOD  1. 所有的线程安全的集合都是以Synchronized 开头的集合
   *
   * SynchronizedBuffer
   * ~Map
   * ~PriorityQueue
   * ~Queue
   * ~Set
   * ~Stack
   *
   * 2. 进程内的线程 共享进程资源。
   * 3. Scala 为了充分使用多核CPU，提供了并行集合(有别与前面的串行集合)，用于多核环境的并行计算。
   * 4. 主要用到的算法：
   * 4.1）Divide and conquer:分治算法，Scala通过splitter（分解器），combiners(核并器)等抽象层来实现，主要原理是
   * 将计算工作分解成很多任务，分发给一些处理器去实现，并将它们处理结果合并返回。
   * 4.2）Work stealin 算法，主要用于任务调度负载均衡（load-balancing）,通俗点完成自己的所有任务后，
   * 发现其他人还有活没干完，主动（或者被安排）帮其他人一起干，这样达到尽早干完任务的目的。
   *
   * 5.并行和并发的概念：并行是真正的多核，一核处理一个线程；  并发是一核处理多个线程；
   *
   */
object 集合线程安全基本介绍 {
  def main(args: Array[String]): Unit = {
    exam2
  }
  // parallel 并行打印 1 ~ 5
  def exam01(): Unit ={
    //串行打印
    (1 to 5).foreach(println)
    println("================")
    //并行打印
    (1 to 5).par.foreach(x=> {
      val name: String = Thread.currentThread.getName
      println(name + "->" + x)
    })
  }
  //查看并行集合张红元素访问的线程
  def exam2(): Unit ={
    val result1: immutable.IndexedSeq[String] = (1 to 10).map{case i => Thread.currentThread.getName +":"+i}
    println(result1)
    println("======================")
    val result2: ParSeq[String] = (1 to 10).par.map{case i => Thread.currentThread.getName +":"+i}
    result2.foreach(println)


  }
}


















