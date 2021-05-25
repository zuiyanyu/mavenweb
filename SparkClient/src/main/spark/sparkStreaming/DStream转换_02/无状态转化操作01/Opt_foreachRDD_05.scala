package sparkStreaming.DStream转换_02.无状态转化操作01
import org.apache.spark.{SparkConf, rdd}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Opt_foreachRDD_05 {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
        val rddQueue = new mutable.Queue[RDD[Int]]()

        val ssc = new StreamingContext(sparkConf, Seconds(2))
        // consume from rddQueue
        val lines = ssc.queueStream(rddQueue)
        // foreachRDD
        lines.foreachRDD(rdd => {

            val values = rdd.take(10)
            for (value <- values) println("foreachRDD == " + value)
        })

        ssc.start()

        // produce to rddQueue
        for (i <- 1 to 30) {
            rddQueue.synchronized {
                rddQueue += ssc.sparkContext.makeRDD(1 to 1000, 10)
            }
            Thread.sleep(1000)
        }
        ssc.stop()
    }

    def iteratorAdd(input: Iterator[Int]) : Iterator[String] = {
        val output = ListBuffer[String]()
        for (t <- input){
            output += t.toString + " map"
        }
        output.iterator
    }

}
