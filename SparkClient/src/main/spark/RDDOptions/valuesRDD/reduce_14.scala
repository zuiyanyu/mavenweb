package RDDOptions.valuesRDD

//TODO 将RDD中的元素进行聚合
/**
  * 处理流程：会先将所有分区的元素拉取到封装到一个数组中，然后对所有数据进行两两聚合，最终得到一个数据。
  * 缺点：如果数据量过大，就会有内存溢出的风险。
  */
object reduce_14 extends SC{
    //TODO  需求：求RDD元素中的最大值
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(List(2,1,3,4))

        /**
          * INFO TaskSetManager: Finished task 1.0 in stage 0.0 (TID 1) in 35 ms on localhost (executor driver) (1/2)
          * INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 63 ms on localhost (executor driver) (2/2)
          *
          * shuffle用时： 35 ms +  63 ms
          */
        var maxValue: Int = rdd.reduce(Math.max(_,_))
        println("maxValue = " + maxValue)

        //TODO 优化
        /**
          * INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 2) in 6 ms on localhost (executor driver) (1/2)
          * TaskSetManager: Finished task 1.0 in stage 1.0 (TID 3) in 6 ms on localhost (executor driver) (2/2)
          *
          * shuffle用时： 6ms + 6ms = 12 ms
          */
        maxValue = rdd.glom().map(_.max).reduce(_ max _)
        println("maxValue = " + maxValue)

    }
}
