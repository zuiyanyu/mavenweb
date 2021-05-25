package sparkStreaming.Dstream创建_01.KafkaSource.direct

import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka._

object KafkaConsumer_direct_01 {
    val group = "consumer1"
    val brokers = "hadoop102:9092"

    def main(args: Array[String]): Unit = {
        direct
    }
    def direct()={
        val conf = new SparkConf().setMaster("local[2]").setAppName("kafka test")
        val ssc = new StreamingContext(conf,Seconds(10))

        val topics = "kafka_topic"

        val topicsSet = topics.split(",").toSet
        val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers,"bootstrap.servers"->brokers)

        //底层 默认会获取topic对应的分区leader上的offset
        // val fromOffsets = getFromOffsets(kc, kafkaParams, topics)
        //val messageHandler = (mmd: MessageAndMetadata[String, String]) => (mmd.message)
        val messages: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
            ssc, kafkaParams, topicsSet)


        val lines = messages.map(_._2)
        val words = lines.flatMap(_.split(" ")).map(x=>(x,1))

        words.reduceByKey(_ + _).print()
        ssc.start()
        ssc.awaitTermination()
    }
}
