package scala集合.java集合和scala集合互转

import java.util

import scala.collection.mutable

/**
  *
  * //TODO java和scala的互转，需要引入JavaConversions 类，类声明 object JavaConversions extends WrapAsScala with WrapAsJava
  * 从类声明可以看出看出，JavaConversions 继承了WrapAsScala ，WrapAsJava 两个包装类(封装了类型隐士转换规则)：
  * WrapAsScala ：将 java集合转换为 scala集合
  * WrapAsJava  ：将scala集合转为java集合
  *
  *
  */
object javaList_to_scalaBuffer {
    def main(args: Array[String]): Unit = {
        javaArrToScalaList
    }
    /**
      * java 的list转Scala数组 mutable.Buffer
      */
    def javaArrToScalaList():Unit ={

        //java.util.List ==> scala.Buffer
        var javaList: util.List[String] = new util.ArrayList[String]()
        javaList.add("1")
        javaList.add("2")
        javaList.add("3")

        //java 的list转Scala数组 mutable.Buffer
        //TODO JavaConversions.asScalaBuffer的声明: implicit def asScalaBuffer[A](l: ju.List[A]): mutable.Buffer[A]
        //TODO ju就是java.util的缩写。 可以看出，是使用def的隐士转换方式进行了类型转换，将ju.List 转换为了mutable.Buffer
        import scala.collection.JavaConversions.asScalaBuffer
        val scalaArr:mutable.Buffer[String] = javaList

        println(scalaArr.getClass)  //class scala.collection.convert.Wrappers$JListWrapper
        println(scalaArr)

        //  def +=:(elem: A) = { underlying.subList(0, 0) add elem; this }
        // underlying  就是要被隐士转的 ju.List[A] ，即javaList实例
        println(scalaArr.+=:("44"))
    }
}
