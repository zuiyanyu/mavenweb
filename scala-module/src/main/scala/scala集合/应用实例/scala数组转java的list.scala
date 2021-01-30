package scala集合.应用实例

import java.util

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object scala数组转java的list {
  def main(args: Array[String]): Unit = {
    //TODO scala数组和java 集合相互转换
    scalaArrayToJavaList
    javaArrToScalaList

  }

  /**
    *  scala数组 转java  list集合
    */
  def scalaArrayToJavaList(): Unit ={
    val scalaArr = ArrayBuffer("1","2","3")

    import scala.collection.JavaConversions.bufferAsJavaList
    val javaArr: ProcessBuilder = new ProcessBuilder(scalaArr)
    val javaList: util.List[String] = javaArr.command()
    println(javaList)

  }

  /**
    * java 的list转Scala数组 mutable.Buffer
    */
  def javaArrToScalaList(): Unit ={

    //java.util.List ==> scala.Buffer
    var javaList: util.List[String] = new util.ArrayList[String]()
    javaList.add("1")
    javaList.add("2")
    javaList.add("3")

    var javaList2: util.List[Int] = new util.ArrayList[Int]()
    javaList2.add(1)


    //java 的list转Scala数组 mutable.Buffer
    //import scala.collection.JavaConversions.asScalaBuffer
    import scala.collection.JavaConversions._

    val strings: util.ArrayList[String] = javaList.asInstanceOf[util.ArrayList[String]]

    val scalaArr:mutable.Buffer[String] = javaList
    val scalaArr2:mutable.Buffer[Int] = javaList2

    println(scalaArr)
    println(scalaArr2)


  }

}
