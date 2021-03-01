package 数据读取与保存.文件类读取与保存

import SC.SC
import org.apache.spark.rdd.RDD

object Text_File extends SC{
    def main(args: Array[String]): Unit = {

        val inputFilePath = "D:/hadoop/textFile/input/fruit.txt" ;
        //1）数据读取:textFile(String)
        //val hdfsFile = sc.textFile("hdfs://hadoop102:9000/fruit.txt")
        val hdfsFile:RDD[String] = sc.textFile(inputFilePath)


        val outputFilePath = "D:/hadoop/textFile/output" ;
        //2）数据保存: saveAsTextFile(String)   输出路径不能存在，否则报错
        //hdfsFile.saveAsTextFile("/fruitOut")
        hdfsFile.coalesce(1).saveAsTextFile(outputFilePath)

        println("ok")

    }
}
