package scala集合

import scala.collection.mutable

object Model {
    def main(args: Array[String]): Unit = {
        function
        add
        query
        define

    }
    //TODO 4.  功能介绍
    def function(): Unit ={
        println("=========TODO 4.  功能介绍===============")

    }

    //TODO 3. 元素添加
    def add(): Unit ={
        println("==========TODO 3. 元素添加==============")

    }
    //TODO 2.  遍历方式
    def query(): Unit ={
        println("=========TODO 2.  遍历方式===============")

    }
    //TODO 1.  声明方式
    def define(): Unit ={
        println("==========TODO 1.  声明方式==============")

    }
    def print_[T](seq:mutable.Seq[T]): Unit ={
        println(seq.mkString("\t"))
    }
}
