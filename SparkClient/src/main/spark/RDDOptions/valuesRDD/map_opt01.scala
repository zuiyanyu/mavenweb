package RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

//map()  作用：返回一个新的RDD，该RDD由每一个输入元素经过func函数转换后组成
object map_opt01 extends SC{

    //TODO 需求：创建一个RDD，使每个元素*2组成新的RDD
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array(1,2,3,4))
        val newRdd: RDD[Int] = rdd.map(_ * 2)
        val valueList: Array[Int] = newRdd.collect()
        valueList.foreach(println)
    }
}
