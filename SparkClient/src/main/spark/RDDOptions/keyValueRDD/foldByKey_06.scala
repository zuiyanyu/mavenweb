package RDDOptions.keyValueRDD

/**
  * 参数：(zeroValue: V)(func: (V, V) => V): RDD[(K, V)
  * 1. 作用：aggregateByKey的简化操作，seqop和combop相同
  * reduceByKey作用相同，只是reduceByKey没有额外的初始值zeroValue
  */
object foldByKey_06 extends SC{
    //TODO 2. 需求：创建一个pairRDD，计算相同key对应值的相加结果
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(List((1,3),(1,2),(1,4),(2,3),(3,6),(3,8)),3)

        //计算相同key对应值的相加结果  也会进行预聚合操作，和reduceByKey作用相同
        val agg = rdd.foldByKey(0)(_+_)

        //打印结果
        listPrint(agg.collect())

    }
}
