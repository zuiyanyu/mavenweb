package sparkStreaming.DStream转换_02.无状态转化操作01

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Opt_mapPartitions_03 {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
        val rddQueue = new mutable.Queue[RDD[Int]]()

        val ssc = new StreamingContext(sparkConf, Seconds(2))
        val lines = ssc.queueStream(rddQueue)

        // flatMap & mapPartitions
        val d = lines.flatMap(List(_, 20, 30, 40)).mapPartitions(iteratorAdd)

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

    //进入一个分区的所有数据，返回一个分区映射值
    def iteratorAdd(input: Iterator[Int]) : Iterator[String] = {
        val output = ListBuffer[String]()
        for (t <- input){
            output += t.toString + " map"
        }
        //分区新值
        output.iterator
    }
}
