package sparkcore.RDD中的函数传递.传递一个属性

import sparkcore.RDD中的函数传递.Search
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object TransmitTest {

    def main(args: Array[String]): Unit = {

        //1.初始化配置信息及SparkContext
        val sparkConf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(sparkConf)

        //2.创建一个RDD
        val rdd: RDD[String] = sc.parallelize(Array("hadoop", "spark", "hive", "abcd"))

        //3.创建一个Search对象
        val search = new Search("abc")

        //4.运用第一个过滤函数并打印结果
        //TODO 传递一个属性 query:String 到executor，属性所属对象需要序列化
        val match1: RDD[String] = search.getMatche2(rdd)
        match1.collect().foreach(println)
    }
}
