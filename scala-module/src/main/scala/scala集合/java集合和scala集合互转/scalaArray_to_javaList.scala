package scala集合.java集合和scala集合互转

import java.util

import scala.collection.mutable.ArrayBuffer

object scalaArray_to_javaList {
    def main(args: Array[String]): Unit = {
        scalaArrayToJavaList
    }
    /**
      *  scala数组 转java  list集合
      */
    def scalaArrayToJavaList(): Unit ={
        val scalaArr = ArrayBuffer("1","2","3")

        //TODO 固定步骤
        import scala.collection.JavaConversions.bufferAsJavaList
//        val javaArr: ProcessBuilder = new ProcessBuilder(scalaArr)
//        val javaList: util.List[String] = javaArr.command()
//        println(javaList)
        
        val javaList2: util.List[String] =scalaArr
        println(javaList2)

    }
}
