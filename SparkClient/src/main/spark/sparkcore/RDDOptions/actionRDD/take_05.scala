package sparkcore.RDDOptions.actionRDD

/**
  * 1. 作用：返回一个由RDD的前n个元素组成的数组
  *
  */
object take_05 extends SC{
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array(2,5,4,6,8,3))
        val takeResult: Array[Int] = rdd.take(4)

        /**
          * [2,5,4]
          * [6,8,3]
          */
        glomPrint(rdd)

        listPrint(takeResult) // [ 2,5,4 ]
    }
}
