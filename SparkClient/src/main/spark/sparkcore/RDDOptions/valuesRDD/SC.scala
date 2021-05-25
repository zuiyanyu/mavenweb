package sparkcore.RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

class SC {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("RDDOption");
    val sc = new SparkContext(sparkConf)

    def glomPrint[T](rdd:RDD[T]): Unit ={
        val glom: Array[Array[T]] = rdd.glom().collect()
        println("---------glom print start----------")
        for (elemArray <- glom) {
            println("["+elemArray.mkString(",")+"]")
        }
        println("---------glom print end----------")

    }
    def listPrint(iter:Iterable[Any]): Unit ={
        println("---------listPrint start----------")
        val str: String = iter.mkString(",")
        println("[ " + str + " ]")
        println("---------listPrint end----------")
    }
}
