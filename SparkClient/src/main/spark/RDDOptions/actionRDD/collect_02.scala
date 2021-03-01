package RDDOptions.actionRDD

/**
  * 1. 作用：在驱动程序中，以数组的形式返回数据集的所有元素。
  * 2. 需求：创建一个RDD，并将RDD内容收集到Driver端打印
  */
object collect_02 extends SC{
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(1 to 10)

        //将结果收集到Driver端
        val collect: Array[Int] = rdd.collect

        //输出结果
        listPrint(collect) //[ 1,2,3,4,5,6,7,8,9,10 ]
    }
}
