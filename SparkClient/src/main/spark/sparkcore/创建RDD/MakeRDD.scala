package sparkcore.创建RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

object MakeRDD {


    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf()
        sparkConf.setAppName("sparkcore/wordCount")
        sparkConf.setMaster("local[2]");
        //2.获取spark的上下文环境  spark-core的核心入口
        val sc = new SparkContext(sparkConf)
        val value: RDD[String] = sc.textFile("")

        //5.关闭连接
        sc.stop()

    }

    /**
      * 使用parallelize()从集合创建RDD
      * @param sc
      */
    def makeRDD_byCollection(sc:SparkContext): Unit ={
        val rdd = sc.parallelize(Array(1,2,3,4,5,6,7,8))
        val value: RDD[Int] = sc.makeRDD(Array(1,2,3,4,5,6,7,8))


    }
}
