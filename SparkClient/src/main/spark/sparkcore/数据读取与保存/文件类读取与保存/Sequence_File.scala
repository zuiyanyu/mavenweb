package sparkcore.数据读取与保存.文件类读取与保存

import sparkcore.SC.SC

/**
  * SequenceFile文件是Hadoop用来存储二进制形式的key-value对而设计的一种平面文件(Flat File)。
  * Spark 有专门用来读取 SequenceFile 的接口。在 SparkContext 中，可以调用 sequenceFile[ keyClass, valueClass](path)。
  * 注意：SequenceFile文件只针对PairRDD
  */
object Sequence_File extends SC{
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array((1,2),(3,4),(5,6)))

        val filePath = "D:\\hadoop\\sequenceFile\\seqFile" ;
        //（2）将RDD保存为Sequence文件
        rdd.saveAsSequenceFile(filePath)
        println("文件保存完毕！")


        //（4）读取Sequence文件
        val seq = sc.sequenceFile[Int,Int](filePath)
        seq.collect.foreach(println)

     }
}
