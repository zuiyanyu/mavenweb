package sparkcore.RDDOptions.actionRDD

/**
  * 1. 作用：返回RDD中元素的个数
  * 2. 需求：创建一个RDD，统计该RDD的条数
  */
object count_03 extends SC{
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(1 to 10)
        println(rdd.count())
    }
}
