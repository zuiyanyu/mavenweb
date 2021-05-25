package sparkcore.RDDOptions.valuesRDD

/**
  * 类似于map，但是每一个输入元素可以被映射为0个或多个输出元素（所以func应该返回一个序列，而不是单一元素）
  */
object flatMap_opt04 extends SC{
    //TODO . 需求：创建一个元素为1-5的RDD，运用flatMap创建一个新的RDD，新的RDD为原RDD的每个元素的2倍（2，4，6，8，10）
    def main(args: Array[String]): Unit = {
        val sourceFlat = sc.parallelize(1 to 5)
        val values: Array[Int] = sourceFlat.collect()
        println(values.mkString(","))

        //（3）根据原RDD创建新RDD（1->1,1->2,1->3,1->4,1->5）
        val flatMap = sourceFlat.flatMap(1 to _)

        //（4）打印新RDD
        listPrint(flatMap.collect()) //[ 1,1,2,1,2,3,1,2,3,4,1,2,3,4,5 ]
    }

}
