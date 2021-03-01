package RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD

/**
  * 1. 作用：类似于map，但独立地在RDD的每一个分片上运行，
  * 因此在类型为T的RDD上运行时，func的函数类型必须是Iterator[T] => Iterator[U]
  */
object mapPartitions_opt02 extends SC{
    //TODO 需求：创建一个RDD，使每个元素*2组成新的RDD
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array(1,2,6,9,3,4))
         rdd.mapPartitions(iter=>iter.map(_*2))
                .collect()
                .foreach(println)


    }
}
