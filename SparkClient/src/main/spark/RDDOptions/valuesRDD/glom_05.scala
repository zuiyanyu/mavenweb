package RDDOptions.valuesRDD

import RDDOptions.valuesRDD.groupBy_06.{glomPrint, sc}
import org.apache.spark.rdd.RDD

//TODO 作用：将每一个分区形成一个数组，形成新的RDD类型: RDD[Array[T]]  ， RDD的元素由 T 变成了Array[T]
object glom_05 extends SC{


    def main(args: Array[String]): Unit = {
        glom_02
    }

    //2.glom的应用
    //TODO 求各个分区最大值
    def glom_02(): Unit ={
        //（1）创建
        val rdd: RDD[Int] = sc.parallelize(1 to 12,3)
        glomPrint(rdd)
        /**
          * [1,2,3,4]
          * [5,6,7,8]
          * [9,10,11,12]
          */

        //TODO 方案1 先将所有的数据拉取到一台服务器上对应分区中，然后将分区内的元素封装成一个数组，让服务器上的一个executor处理所有数据
        //TODO 如果数据量很大，那么就有内存溢出的风险
        val maxValue: Int = rdd.reduce(Math.max(_,_))
        println(maxValue)

        //TODO 方案2：各个executor先进行区内求最大值，然后将各个executor对每个区的计算结果都发送到同一个executor上，进行区间求最大值
        //减少网络数据传输量
        val max_value: Int = rdd
                .glom()                //元素 => 集合
                .map(list=>list.max )  //集合 => 元素 (最大元素)
                .reduce(_ max _)       //区间元素聚合
        print(max_value)

    }
    //TODO 需求：创建一个4个分区的RDD，并将每个分区的数据放到一个数组
    //1.glom的语法
    def glom_01(): Unit ={
        val rdd = sc.parallelize(1 to 16,4)

        //将每个分区的数据放到一个数组并收集到Driver端打印
        val glom: Array[Array[Int]] = rdd.glom().collect()

        for (elemArray <- glom) {
            println("["+elemArray.mkString(",")+"]")
        }
    }
}
