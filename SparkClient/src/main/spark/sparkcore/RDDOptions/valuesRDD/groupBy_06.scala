package sparkcore.RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD

//TODO 作用：分组，按照传入函数的返回值进行分组。将相同key对应的值放入一个迭代器
object groupBy_06 extends SC {
    def main(args: Array[String]): Unit = {
        groupByTest
    }
    //TODO 创建一个RDD，按照元素模以2的值进行分组。
    def groupByTest(): Unit ={
        //（1）创建
        val rdd: RDD[Int] = sc.parallelize(1 to 4)

        //（2）按照元素模以2的值进行分组 RDD[(key,聚合的结果）]
        val group: RDD[(Int, Iterable[Int])] = rdd.groupBy(_%2)
        glomPrint(group)
        /** 输出结果：
          * [(0,CompactBuffer(2, 4))]
          * [(1,CompactBuffer(1, 3))]
          */

        // =============等价效果 如下=====================
        //为每一个value指定一个key
        val keyRdd: RDD[(Int, Int)] = rdd.map(x=>(x%2,x))
        //根据key进行聚合
        val groupByKey: RDD[(Int, Iterable[Int])] = keyRdd.groupByKey()
        glomPrint(groupByKey)
        /** 输出结果：
          * [(0,CompactBuffer(2, 4))]
          * [(1,CompactBuffer(1, 3))]
          */
    }

}
