package sparkStreaming.Dstream创建_01

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Duration, StreamingContext}

object Socket_socketTextStream_02 {
    def main(args: Array[String]): Unit = {
        println(Socket_socketTextStream_02.getClass)

        println(Socket_socketTextStream_02.getClass.getSimpleName)

        println(Socket_socketTextStream_02.getClass.getName)
    }
    def main2(args:Array[String])={
        //1.初始化Spark配置信息
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("StreamWordCount")

        //2.初始化SparkStreamingContext   case class Duration (private val millis: Long)  millis：毫秒
        //  10秒内接收的数据保存成一个批次数据。数据量多的话会有多个block数据块产生，一个block块一个分区(猜测) . Seconds(5)
        val streamingContext = new StreamingContext(sparkConf, Duration(10 * 1000))

        //3.通过监控端口创建DStream，读进来的数据为一行行
        val lineStreams: ReceiverInputDStream[String] = streamingContext.socketTextStream("hadoop103",9999)

        //4.将每一行数据做切分，形成一个个单词
        val wordStreams : DStream[String] = lineStreams.flatMap(_.split(" "))

        //5.将单词映射成元组（word,1）
        val wordAndOneStreams = wordStreams.map((_, 1))

        //6.将相同的单词次数做统计
        val wordAndCountStreams : DStream[(String, Int)] = wordAndOneStreams.reduceByKey(_+_)

        //7.打印
        wordAndCountStreams.print()

        //8. TODO 启动SparkStreamingContext
        streamingContext.start() //启动流监控
        streamingContext.awaitTermination()//等待流处理结束

    }

}
