package sparkcore.RDDOptions.actionRDD

/**
  * 1. 作用：在数据集的每一个元素上，运行函数func进行更新。
  * 2. 需求：创建一个RDD，对每个元素进行打印
  */
object foreach_11 extends SC{
    def main(args: Array[String]): Unit = {
        var rdd = sc.makeRDD(1 to 5,2)

        /**
          * [1,2]
          * [3,4,5]
          */
        glomPrint(rdd)

        //对该RDD每个元素进行打印
         rdd.foreach(print(_)) // 34512

        rdd.foreachPartition(iter=>iter.foreach(println))
    }
}
