package RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD

/**
  * 作用：类似于mapPartitions，但func带有一个整数参数表示分片的索引值，
  * 因此在类型为T的RDD上运行时，func的函数类型必须是(Int, Interator[T]) => Iterator[U]；
  */
object mapPartitionsWithIndex_opt03 extends SC{
    //TODO 需求：创建一个RDD，使每个元素跟所在分区索引形成一个元组组成一个新的RDD,即
    //TODO 查看每个元素的所在分区
    def main(args: Array[String]): Unit = {
        //设置并行度为3， local模式下，会根据并行度形成相同数量的分区
        val rdd: RDD[Int] = sc.makeRDD(Array(1,2,3,4,5,6),3)
        //查看各个分区的数据情况
        glomPrint(rdd)
        /** 打印结果
          * [1,2]
          * [3,4]
          * [5,6]
          */

        val tupleRDD: RDD[(Int, Int)] = rdd.mapPartitionsWithIndex((partitionIndex, partitionValuesIter) => {
            //将每个元素和分区号组成一个新的元素：元组
            partitionValuesIter.map((_, partitionIndex))
        })
        glomPrint(tupleRDD)

        /**  打印结果
          * [(1,0),(2,0)]
          * [(3,1),(4,1)]
          * [(5,2),(6,2)]
          */

    }
}
