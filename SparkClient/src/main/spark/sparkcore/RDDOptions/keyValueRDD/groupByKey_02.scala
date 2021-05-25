package sparkcore.RDDOptions.keyValueRDD

import org.apache.spark.rdd.RDD

//TODO 1. 作用：groupByKey也是对每个key进行操作，但只生成一个sequence。
object groupByKey_02 extends SC {
    //2. 需求：创建一个pairRDD，将相同key对应值聚合到一个sequence中，并计算相同key对应值的相加结果。
    def main(args: Array[String]): Unit = {
        val words = Array("one", "two", "two", "three", "three", "three")

        val wordPairsRDD = sc.parallelize(words).map(word => (word, 1))
        glomPrint(wordPairsRDD)
        /** 结果： 根据结果，可以看到数据被分配到了两个分区中
          * [(one,1),(two,1),(two,1)]
          * [(three,1),(three,1),(three,1)]
          */

        //TODO 将相同key对应值聚合到一个sequence中  ,会经过shuffle
        val group = wordPairsRDD.groupByKey()
        //打印结果
        // 根据打印结果，可以看到两个分区的数据出现了数据倾斜。
         glomPrint(group) // [(two,CompactBuffer(1, 1)),(one,CompactBuffer(1)),(three,CompactBuffer(1, 1, 1))]
        /**
          * [(two,CompactBuffer(1, 1)),(one,CompactBuffer(1)),(three,CompactBuffer(1, 1, 1))]
          * []
          */

        // 计算相同key对应值的相加结果
        val countRDD: RDD[(String, Int)] = group.map{case(key,list) => (key, list.sum)}
        glomPrint(countRDD)
        /**
          * [(two,2),(one,1),(three,3)]
          * []
          */

        //TODO 优化方案  使用reduceByKey 先进行区内聚合，然后再进行区间聚合
        val reduceByKeyRdd: RDD[(String, Int)] = wordPairsRDD.reduceByKey(_+_)
        glomPrint(reduceByKeyRdd)
        /**
          * [(two,2),(one,1),(three,3)]
          * []
          */

    }
}
