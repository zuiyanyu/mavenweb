package sparkcore.RDDOptions.actionRDD

/**
  * 作用：将数据集的元素以textfile的形式保存到HDFS文件系统或者其他支持的文件系统，
  * 对于每个元素，Spark将会调用toString方法，将它装换为文件中的文本
  */
object saveAsTextFile_09 extends SC{
    def main(args: Array[String]): Unit = {
        val fileSavePath = "D:\\hadoop\\saveAsTextFile\\rdd1"
        var rdd = sc.makeRDD(1 to 10,2)
        //TODO 一个分区，会保存成一个文件
        rdd.saveAsTextFile(fileSavePath)
        println("ok")
    }
}
