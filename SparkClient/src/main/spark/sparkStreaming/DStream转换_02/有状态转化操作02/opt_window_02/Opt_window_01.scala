package sparkStreaming.DStream转换_02.有状态转化操作02.opt_window_02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object Opt_window_01 {
    def main(args: Array[String]): Unit = {
        //1.初始化spark的配置信息
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("DStream")
        //2.初始化sparkStreamingContext 3秒一个批次
        val streamingContext = new StreamingContext(sparkConf,Seconds(3))

        //3.通过监控端口创建DStream，读进来的数据为一行一行的
        //即 监控端口实时读取数据 按行读取
        val socketDStream: ReceiverInputDStream[String] =
        streamingContext.socketTextStream("hadoop102",1234)

        //TODO   3秒一个批次，窗口6秒，滑步3秒。3秒一个批次，窗口6秒，滑步3秒。
        //TODO   将一个窗口中的数据作为一个整体
        val windowDStream: DStream[String] = socketDStream.window(Seconds(6),Seconds(3))

        //4. 将每一行数据进行切分，形成一个一个的单词 .按空格切分
        val wordsStream: DStream[String] = windowDStream.flatMap(line=>{line.split(" ")})

        //5. 将单词映射成元组(word,1)
        val wordAndOneStream: DStream[(String, Int)] = wordsStream.map{(_,1)}

        //6.将相同的单词进行分组统计
        val wordAndCountsStream: DStream[(String, Int)] = wordAndOneStream.reduceByKey(_+_)

        //7.打印统计的结果
        wordAndCountsStream.print()

        //8.启动SparkStreamingContext
        streamingContext.start()
        streamingContext.awaitTermination()
    }
}
