package sparkcore.RDDOptions.keyValueRDD

/**
  * 1. 在一个(K,V)的RDD上调用，返回一个(K,V)的RDD，使用指定的reduce函数，将相同key的值聚合到一起，
  * reduce任务的个数可以通过第二个可选的参数来设置。
  */
object reduceByKey_03 extends SC{
    //2. 需求：创建一个pairRDD，计算相同key对应值的相加结果
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(List(("female",1),("male",5),("female",5),("male",2)))

        //计算相同key对应值的相加结果
        val reduce = rdd.reduceByKey((x,y) => x+y)

        //打印结果
        listPrint(reduce.collect())
    }

}
