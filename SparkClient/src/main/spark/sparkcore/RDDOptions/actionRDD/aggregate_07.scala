package sparkcore.RDDOptions.actionRDD

import sparkcore.SC.SC

/**
  * 1. 参数：(zeroValue: U)(seqOp: (U, T) ⇒ U, combOp: (U, U) ⇒ U)
  * 2. 作用：aggregate函数将每个分区里面的元素通过seqOp和初始值进行聚合，
  *    然后用combine函数将每个分区的结果和初始值(zeroValue)进行combine操作。这个函数最终返回的类型不需要和RDD中元素类型一致。
  *
  *    即：分区内聚合用到了初始值，并且分区间聚合也用到了初始值
  *
  * 3. 需求：创建一个RDD，将所有元素相加得到结果
  */
object aggregate_07 extends SC{
    def main(args: Array[String]): Unit = {
        var rdd1 = sc.makeRDD(1 to 10,2)

        /** 两个分区：
          * [1,2,3,4,5]
          * [6,7,8,9,10]
          */
        glomPrint(rdd1)


        // 将该RDD所有元素相加得到结果
        var result: Int = rdd1.aggregate(0)(_+_,_+_)
        println(result) // 55

        // 将该RDD所有元素相加得到结果
        result  = rdd1.aggregate(1)(_+_,_+_)
        println(result) // 58  两个分区，区内各加了一次初始值1， 两个分区进行区间合并的时候，又加了一次初始值1，所以总结果多了3
    }
}
