package RDDOptions.keyValueRDD

//TODO 作用：对pairRDD进行分区操作，如果原有的partionRDD和现有的partionRDD是一致的话就不进行分区，
// TODO 否则会生成ShuffleRDD，即会产生shuffle过程。
object partitionBy_01 extends SC {
    def main(args: Array[String]): Unit = {
        //1.创建一个4个分区的RDD
        val rdd = sc.parallelize(Array((1,"aaa"),(2,"bbb"),(3,"ccc"),(4,"ddd")),4)
        //2.查看RDD的分区数
        println("分区数 = " + rdd.partitions.size) //新分区数 = 2
        glomPrint(rdd)
        /**
          * [(1,aaa)]
          * [(2,bbb)]
          * [(3,ccc)]
          * [(4,ddd)]
          */

        //3.对RDD重新分区  分区器根据key来决定当前value分到哪个分区中
        //如果 RDD持有的分区器 和 入参指定的分区器是同一个，那么就不进行分区。
        var rdd2 = rdd.partitionBy(new org.apache.spark.HashPartitioner(2))
        println("新分区数 = " + rdd2.partitions.size) //新分区数 = 2
        glomPrint(rdd2)
        /**
          * [(2,bbb),(4,ddd)]
          * [(1,aaa),(3,ccc)]
          */


    }
}
