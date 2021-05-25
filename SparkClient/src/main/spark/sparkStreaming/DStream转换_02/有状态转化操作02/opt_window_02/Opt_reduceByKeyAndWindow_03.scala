package sparkStreaming.DStream转换_02.有状态转化操作02.opt_window_02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object Opt_reduceByKeyAndWindow_03 {
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
        /**
          *  reduceFunc: 结合律和交换律的约化函数
          *  invReduceFunc:可逆函数 。
          *  比如：对于所有的y值，和可逆的x值，invReduceFunc(reduceFunc(x, y), x) = y
          *  即：reduceFunc(x,y)将x,y值进行聚合，invReduceFunc将 reduceFunc(x, y) 的聚合结果 和 x
          *  进行和reduceFunc相反的操作得到y值。
          *
          */
        val wordCounts = pairs.reduceByKeyAndWindow(
            (a:Int ,b:Int) => a + b  ,   // 加上新进入窗口的批次中的元素
            (ab:Int,a:Int) => ab - a ,    //移除离开窗口的老批次中的元素
            Seconds(6),
            Seconds(3))

        // Print the first ten elements of each RDD generated in this DStream to the console
        wordCounts.print()

        ssc.start()             // Start the computation
        ssc.awaitTermination()  // Wait for the computation to terminate
    }
}
