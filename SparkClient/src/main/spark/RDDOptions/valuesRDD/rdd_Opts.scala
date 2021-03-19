package RDDOptions.valuesRDD

import RDDOptions.valuesRDD.mapPartitionsWithIndex_opt03.sc
import org.apache.spark.rdd.RDD

object rdd_Opts extends SC {
    def main(args: Array[String]): Unit = {
        rdd_setName
    }
    def rdd_setName: Unit ={
        val rdd: RDD[Int] = sc.makeRDD(Array(1,2,3,4,5,6),3)
        val rdd2: RDD[Int] = rdd.setName("setName")
        val array: Array[Int] = rdd2.collect()

        listPrint(array)

        println("rdd2.count() = " + rdd2.count())
    }
}
