package scala集合.List集合

import scala.collection.mutable.ListBuffer

object Seq_ListBuffer {
    def main(args: Array[String]): Unit = {
        add
        query
        function
    }
    //TODO 4. ListBuffer的功能介绍
    def function(): Unit ={
        println("========TODO 4. ListBuffer的功能介绍===============")
        val buffer: ListBuffer[Int] = ListBuffer(1,2,1)

        //TODO 删除第一个指定的元素 （重复的元素也只删除第一个）
        buffer -= 1
        printlnListBuffer(buffer) // 2	1
        buffer -= 1
        printlnListBuffer(buffer) //2

        //TODO 两种方式 修改元素
        buffer.update(0,12)
        printlnListBuffer(buffer) // 12

        buffer(0) = 33 ;
        printlnListBuffer(buffer) //33
    }

    //TODO 3. ListBuffer的元素添加
    def add(): Unit ={
        println("========TODO 3. ListBuffer的元素添加===============")
        val buffer: ListBuffer[Int] = ListBuffer()
        //方式1
        buffer.append(1)
        printlnListBuffer(buffer)  //1

        //方式2
        buffer += 2 ;
        printlnListBuffer(buffer) //1	2





    }
    //TODO 2. ListBuffer的遍历
    def query(): Unit ={
        println("========TODO 2. ListBuffer的遍历===============")
        val buffer: ListBuffer[Int] = ListBuffer(1,2,3,4)
        //方式1 单个元素遍历
        println(buffer(1))

    }
    //TODO 1. ListBuffer的声明
    def define(): Unit ={
        println("========TODO 1. ListBuffer的声明===============")

        //方式1
        val buffer: ListBuffer[Nothing] = ListBuffer()
    }
    def printlnListBuffer[T](buffer:ListBuffer[T]){
        println(buffer.mkString("\t"))
    }

}
