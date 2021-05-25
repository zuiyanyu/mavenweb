package sparkStreaming.DStream转换_02.有状态转化操作02.opt_window_02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * reduceByKeyAndWindow(func, windowLength, slideInterval, [numTasks]):
  * 当在一个(K,V)对的DStream上调用此函数，会返回一个新(K,V)对的DStream，
  * 此处通过对滑动窗口中的所有批次数据使用reduce函数来整合窗口中每个key的value值。
  */
object Opt_reduceByKeyAndWindow_02 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

        //TODO 3秒一个批次
        val ssc = new StreamingContext(conf, Seconds(3))
        ssc.checkpoint("./ck")

        // Create a DStream that will connect to hostname:port, like localhost:9999
        val lines = ssc.socketTextStream("hadoop102", 9999)

        // Split each line into words
        val words = lines.flatMap(_.split(" "))

        //import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3
        // Count each word in each batch
        val pairs = words.map(word => (word, 1))

        //TODO 3秒一个批次，窗口6秒，滑步3秒。
        //TODO  和DStream.reduceByKey()类似，但是这里是应用于滑动窗口
        val wordCounts = pairs.reduceByKeyAndWindow((a:Int,b:Int) => (a + b),Seconds(6), Seconds(3))

        // Print the first ten elements of each RDD generated in this DStream to the console
        wordCounts.print()

        ssc.start()             // Start the computation
        ssc.awaitTermination()  // Wait for the computation to terminate
    }
}
