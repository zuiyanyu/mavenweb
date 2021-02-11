package RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD

//作用：对源RDD进行去重后返回一个新的RDD。  会有shuffle
object distinct_09 extends SC{
    def main(args: Array[String]): Unit = {
        val distinctRdd = sc.parallelize(List(1,2,1,5,2,9,6,1))
        /**
          * 21/02/11 22:19:15 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 2) in 31 ms on localhost (executor driver) (1/2)
          * 21/02/11 22:19:15 INFO TaskSetManager: Finished task 1.0 in stage 1.0 (TID 3) in 30 ms on localhost (executor driver) (2/2)
          * shuffle阶段完成时间：   31ms + 30 ms = 61 ms
          */
        val distinct: RDD[Int] = distinctRdd.distinct()
        listPrint(distinct.collect())  //[ 6,2,1,9,5 ]  ResultStage 1 (collect at distinct_09.scala:10) finished in 0.034 s

        /**
          * 优化：先进行区内去重，然后进行区间去重
          * 21/02/11 22:19:15 INFO TaskSetManager: Finished task 0.0 in stage 3.0 (TID 6) in 10 ms on localhost (executor driver) (1/2)
          * 21/02/11 22:19:15 INFO TaskSetManager: Finished task 1.0 in stage 3.0 (TID 7) in 9 ms on localhost (executor driver) (2/2)
          * shuffle阶段完成时间：   10ms + 9 ms = 19 ms
          */
        val distinct02: RDD[Int] = distinctRdd
                .glom().flatMap(list => list.distinct)  //先进行区内去重
                .distinct() //再进行区间去重
        listPrint(distinct02.collect()) //[ 6,2,1,9,5 ]
    }
}
