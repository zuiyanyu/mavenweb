package sparkStreaming.DStream转换_02.无状态转化操作01


import java.util.Random

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object Opt_repartition_01 {
    def main(args: Array[String]): Unit = {
        val index = 8 ;
        val numPartitions = 9;
        var position = (new Random(index)).nextInt(numPartitions)
        println(position)
    }
    def main2(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
        val rddQueue = new mutable.Queue[RDD[Int]]()

        val ssc = new StreamingContext(sparkConf, Seconds(2))
        val lines = ssc.queueStream(rddQueue)

        // repartition
        val d = lines.repartition(10)


        d.print()

        ssc.start()
        for (i <- 1 to 30) {
            rddQueue.synchronized {
                rddQueue += ssc.sparkContext.makeRDD(1 to 100, 10)
            }
            Thread.sleep(1000)
        }
        ssc.stop()

    }
}

