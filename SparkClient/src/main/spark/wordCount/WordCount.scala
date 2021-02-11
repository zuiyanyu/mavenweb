package wordCount

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 本地Spark程序调试需要使用local提交模式，即将本机当做运行环境，Master和Worker都为本机。
  * 运行时直接加断点调试即可。
  */
object WordCount {
    def main(args: Array[String]): Unit = {
       // val args =  Array[String]("D:\\hadoop\\sparkInput"); ;
        //1.获取spark的配置信息
        val sparkConf = new SparkConf()
        sparkConf.setAppName("wordCount")
        sparkConf.setMaster("local[2]");


        //2.获取spark的上下文环境  spark-core的核心入口
        val sc = new SparkContext(sparkConf)


//        //3.spark session是spark sql的核心入口  这里用不到
//        val spark = new SparkSession(sc)

        //4. 读取文件中的内容到rdd中
        val wordCounts: RDD[String] = sc.textFile("D:\\hadoop\\sparkInput")
        //5. 执行相应的transformation和action  统计，并倒序排序输出结果
        wordCounts
                .flatMap(_.split(","))
                .map((_,1))
                .reduceByKey(_+_)
                .sortBy({
                    case (word,count)=>count
                },false)
                .collect()
                .foreach(println)

        //5.关闭连接
        sc.stop()

    }
}
