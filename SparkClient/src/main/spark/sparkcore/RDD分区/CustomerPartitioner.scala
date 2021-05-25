package sparkcore.RDD分区

class CustomerPartitioner(numParts:Int) extends org.apache.spark.Partitioner{

    //覆盖分区数
    override def numPartitions: Int = numParts

    //覆盖分区号获取函数
    override def getPartition(key: Any): Int = {
        val ckey: String = key.toString
        ckey.substring(ckey.length-1).toInt % numParts
    }
}

