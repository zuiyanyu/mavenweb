package sparkcore.RDDOptions.actionRDD

/**
  * 1. 作用：返回RDD中的第一个元素
  * 2. 需求：创建一个RDD，返回该RDD中的第一个元素
  */
object first_04 extends SC{
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(1 to 10)

        //取第一个元素
        val i: Int = rdd.first()
        println(i)
    }
}
