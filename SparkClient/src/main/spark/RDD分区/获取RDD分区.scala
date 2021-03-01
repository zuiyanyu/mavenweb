package RDD分区

import SC.SC
import org.apache.spark.Partitioner

//TODO 可以通过使用RDD的partitioner 属性来获取 RDD 的分区方式。它会返回一个 scala.Option 对象， 通过get方法获取其中的值。
object 获取RDD分区 extends SC{
    def main(args: Array[String]): Unit = {
        val pairs = sc.parallelize(List((1,1),(2,2),(3,3)))

        //查看RDD的分区器
        val partitioner: Option[Partitioner] = pairs.partitioner
        println(partitioner)

        //TODO 导入HashPartitioner类 ,使用HashPartitioner对RDD进行重新分区
        import org.apache.spark.HashPartitioner
        val partitioned = pairs.partitionBy(new HashPartitioner(2))

        //（5）查看重新分区后RDD的分区器
        println(partitioned.partitioner) // Some(org.apache.spark.HashPartitioner@2)

    }

}
