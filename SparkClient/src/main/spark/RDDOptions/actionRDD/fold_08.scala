package RDDOptions.actionRDD

/**
  * 1. 作用：折叠操作，aggregate的简化操作，seqop和combop一样。
  * 2. 需求：创建一个RDD，将所有元素相加得到结果
  */
object fold_08 extends SC{
    def main(args: Array[String]): Unit = {
        var rdd = sc.makeRDD(1 to 10,2)

          val result: Int = rdd.fold(0:Int)(_+_)
    }
}
