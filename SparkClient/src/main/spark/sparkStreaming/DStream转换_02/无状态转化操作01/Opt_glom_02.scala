package sparkStreaming.DStream转换_02.无状态转化操作01

import org.apache.spark.{SparkConf, rdd}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object Opt_glom_02 {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setAppName("api").setMaster("local[2]")
        val rddQueue = new mutable.Queue[RDD[Int]]()

        val ssc = new StreamingContext(sparkConf, Seconds(2))
        // consume from rddQueue
        val lines = ssc.queueStream(rddQueue)

        //def glom(): DStream[Array[T]]  // DStream的每个分区元素是一个Array集合
        // glom  将每个分区中的数据收集起来,形成一个Array[T] ,代表一个分区中的数据
        lines.glom().map(x => for(i <- x) println("glom ==> " + i)).print()

        ssc.start()

        // produce to rddQueue
        for (i <- 1 to 30) {
            rddQueue.synchronized {
                rddQueue += ssc.sparkContext.makeRDD(1 to 100, 10)
            }
            Thread.sleep(1000)
        }
        ssc.stop()
    }
}
