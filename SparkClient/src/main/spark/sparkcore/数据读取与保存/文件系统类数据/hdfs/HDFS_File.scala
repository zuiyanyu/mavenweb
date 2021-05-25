package sparkcore.数据读取与保存.文件系统类数据.hdfs

import sparkcore.SC.SC
/**
  * Spark的整个生态系统与Hadoop是完全兼容的,所以对于Hadoop所支持的文件类型或者数据库类型,Spark也同样支持.
  * 另外,由于Hadoop的API有新旧两个版本,所以Spark为了能够兼容Hadoop所有的版本,也提供了两套创建操作接口.
  *
  * 对于外部存储创建操作而言,hadoopRDD和newHadoopRDD是最为抽象的两个函数接口,主要包含以下四个参数.
  * 1）输入格式(InputFormat): 制定数据输入的类型,如TextInputFormat等,新旧两个版本所引用的版本分别是
  * org.apache.hadoop.mapred.InputFormat
  * 和
  * org.apache.hadoop.mapreduce.InputFormat (NewInputFormat)
  *
  * 2）键类型: 指定[K,V]键值对中K的类型
  * 3）值类型: 指定[K,V]键值对中V的类型
  * 4）分区值: 指定由外部存储生成的RDD的partition数量的最小值,如果没有指定,系统会使用默认值defaultMinSplits
  */
object HDFS_File extends SC{
    def main(args: Array[String]): Unit = {
        val path = "D:\\hadoop\\input"
        sc.textFile("")



    }
}
