package RDD分区

import SC.SC
import org.apache.spark.rdd.RDD

/**
  * 要实现自定义的分区器，你需要继承 org.apache.spark.Partitioner 类并实现下面三个方法。
  * （1）numPartitions: Int:返回创建出来的分区数。
  * （2）getPartition(key: Any): Int:返回给定键的分区编号(0到numPartitions-1)。
  * （3）equals():Java 判断相等性的标准方法。这个方法的实现非常重要，Spark 需要用这个方法来检查你的分区器对象是否和其他分区器实例相同，这样 Spark 才可以判断两个 RDD 的分区方式是否相同。
  *
  */
object 自定义分区 extends SC{
    // 需求：将相同后缀的数据写入相同的文件.
    // 实现思路：通过将相同后缀的数据分区到相同的分区并保存输出来实现。
    def main(args: Array[String]): Unit = {
        val dataRDD = sc.parallelize(Array((11,2),(21,2),(13,3),(43,6),(56,3),(6,6)))

        //TODO 使用自定义分区器，进行重新分区
        val newRDD: RDD[(Int, Int)] = dataRDD.partitionBy(new CustomerPartitioner(4))

        //打印每个分区中的数据情况
        glomPrint(newRDD)

        /**
          * []
          * [(11,2),(21,2)]
          * [(56,3),(6,6)]
          * [(13,3),(43,6)]
          */

    }
}
