package sparkcore.RDDOptions.actionRDD

/**
  * 1. 作用：返回该RDD排序后的前n个元素组成的数组（默认是升序排序）
  */
object takeOrdered_06 extends SC {
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array(2,5,4,6,8,3))

        //默认是升序排序
        val orded: Array[Int] = rdd.takeOrdered(3)

        listPrint(orded) //[ 2,3,4 ]
    }
}
