package sparkStreaming.Dstream创建_01.KafkaSource.receiver

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaConsumer_receiver_01 {
    val zkQuorum = "hadoop102:2181"
    val group = "consumer1"


    def main(args: Array[String]): Unit = {
        createstream
    }
    /**
    *bin/kafka-console-producer.sh –broker-list localhost:9092 –topic mytest
    */
    def createstream()={
        val conf = new SparkConf().setAppName("kafka test").setMaster("local[2]")

        //设置批处理时间间隔为10秒， 每10秒形成一个block数据
        val ssc = new StreamingContext(conf,Seconds(10));

        val topics = "kafka_topic"
        val numThreads = 1
        val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap

        //createStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topics, storageLevel)
        val lines = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap).map(_._2)

        val words = lines.flatMap(_.split(" ")).map(x=>(x,1))
        words.reduceByKey(_ + _).print()


        ssc.start()
        ssc.awaitTermination()
    }
}
