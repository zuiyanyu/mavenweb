package sparkcore.RDDOptions.keyValueRDD

import org.apache.spark.rdd.RDD

// TODO 针对于(K,V)形式的类型只对V进行操作
object mapValues_09 extends SC{

    //需求：创建一个pairRDD，并将value添加字符串"|||"
    def main(args: Array[String]): Unit = {
        val rdd3 = sc.parallelize(Array((1,"a"),(1,"d"),(2,"b"),(3,"c")))

        //（2）对value添加字符串"|||"   以key进行分组，然后逐个传递value进行操作。
        val mapValuesRDD: RDD[(Int, String)] = rdd3.mapValues(_+"|||")

        listPrint(mapValuesRDD.collect()) //[ (1,a|||),(1,d|||),(2,b|||),(3,c|||) ]
    }
}
