package sparkStreaming.Dstream创建_01

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * 通过使用ssc.queueStream(queueOfRDDs,oneAtATime=true)来创建DStream，每一个推送到这个队列中的RDD，都会作为一个DStream处理。
  */
object RDD_queueStream_01 {
    def main(args: Array[String]): Unit = {
        //1.初始化Spark配置信息
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD_queueStream_01")

        //2.初始化SparkStreamingContext  控制窗口多久滑动一次
        val streamingContext = new StreamingContext(sparkConf, Seconds(4))

        //3.创建RDD队列
        val rddQueue: mutable.Queue[RDD[Int]] = new mutable.Queue[RDD[Int]]()

        //4.创建QueueInputDStream
        // oneAtATime:是否在每个间隔内只使用队列中的一个RDD . false : 将队列中的所有rdd视为一个Stream流。
        // oneAtATime控制窗口的大小，一次框多少数据。
        val inputDstream: InputDStream[Int] = streamingContext.queueStream(rddQueue,oneAtATime=false)

        //5.处理队列中的RDD数据
        val mappedStream : DStream[(Int, Int)] = inputDstream.map((_,1)).filter(_._2 > 1)
        val reducedStream = mappedStream.reduceByKey(_ + _)

        //6.打印结果  每次打印出rdd中一个分区中的数据
        reducedStream.print()

        //7.启动任务
        streamingContext.start()

        //8.循环创建并向RDD队列中放入RDD
        val sc: SparkContext = streamingContext.sparkContext
        for (i <- 1 to 5) {
            //创建一个由10个分区的RDD，数据量有300个， 添加到队列中。
            rddQueue += sc.makeRDD(1 to 300, 10)
            Thread.sleep(2000)
        }

        //等待新任务的到来 或者等待停止命令
        streamingContext.awaitTermination()
    }
}
