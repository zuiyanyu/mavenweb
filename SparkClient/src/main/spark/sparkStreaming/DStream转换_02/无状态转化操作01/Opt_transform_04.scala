package sparkStreaming.DStream转换_02.无状态转化操作01

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Opt_transform_04 {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
        val rddQueue = new mutable.Queue[RDD[Int]]()

        val ssc = new StreamingContext(sparkConf, Seconds(2))
        // 消费RDD队列作为数据源
        val lines = ssc.queueStream(rddQueue)


        // transform example
        val transform1 = lines.transform(rdd => {
            println("transform1: id: " + rdd.id)
            rdd
        })

        val transform2 = transform1.transform(
            (rdd, time) => {
                println("transform2: id: " + rdd.id + " time: " + time)
                rdd
            }
        )
        transform2.print()

        ssc.start()

        // 向RDD队列生成数据
        for (i <- 1 to 30) {
            rddQueue.synchronized {
                rddQueue += ssc.sparkContext.makeRDD(1 to 1000, 10)
            }
            Thread.sleep(1000)
        }
        ssc.stop()
    }
}
