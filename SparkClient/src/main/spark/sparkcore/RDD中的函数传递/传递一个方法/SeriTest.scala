package sparkcore.RDD中的函数传递.传递一个方法

import sparkcore.RDD中的函数传递.Search
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object SeriTest {
    def main(args: Array[String]): Unit = {

        error
    }
    //传递一个方法
    def error: Unit ={
        //1.初始化配置信息及SparkContext
        val sparkConf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(sparkConf)

        //2.创建一个RDD
        val rdd: RDD[String] = sc.parallelize(Array("hadoop", "spark", "hive", "abc", "bcd", "abcabc"))

        //3.创建一个Search对象
        val search = new Search("abc")

        //4.运用第一个过滤函数并打印结果
        //TODO Search对象没有序列化的时候，报异常： object not serializable
        //TODO 序列化之后就正常执行出结果了，到底为什么呢？
        /**
          * 4．问题说明
          * //过滤出包含字符串的RDD
          * def getMatch1 (rdd: RDD[String]): RDD[String] = {
          *     rdd.filter(isMatch)
          * }
          * 在这个方法中所调用的方法isMatch()是定义在Search这个类中的，实际上调用的是this. isMatch()，this表示Search这个类的对象，程序在运行过程中需要将Search对象序列化以后传递到Executor端。
          *
          * 5．解决方案
          * 使类继承scala.Serializable即可。
          * class Search() extends Serializable{...}
          */
        //TODO 传递一个方法 isMatch() 到executor,方法所属对象需要序列化
        val match1: RDD[String] = search.getMatch1(rdd)
        match1.collect().foreach(println)
    }

}
