package sparkStreaming.Dstream创建_01.KafkaSource.receiver

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

class KafkaStreaming_receiver_03 {
    def main(args: Array[String]): Unit = {
        // 一个类如果创建SparkContext,那么这个类我们称之为Driver类
        //环境准备
        // 创建配置对象
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("kafkaStream")
        // 创建流式处理环境对象
        // 创建对象时，需要传递采集数据的周期（时间）
        val streamingContext = new StreamingContext(sparkConf, Seconds(4))

        //从kafka集群中获取数据

        //kafa声明
        //brokers 节点
        // val brokers = "hadoop102:9092,hadoop103:9092,hadoop104:9092"
        val zkQuorum = "hadoop102:2181"
        val topic = "kafka_topic" //主题
        val groupId = "kafka_topic_group02" //消费者组
        //序列化与反序列化
        val deserialization = "org.apache.kafka.common.serialization.StringDeserializer"


        //配置信息
        val kafkaParmeters = Map[String, String](
            ConsumerConfig.GROUP_ID_CONFIG -> groupId,
            // ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG->brokers,  //这里在KafkaUtils.createStream中没起作用
            "zookeeper.connect" -> zkQuorum,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> deserialization,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> deserialization
        )
        val topicsMap = Map[String, Int](topic -> 2) //key=topic value= partitions

        //（key,value）key判断分区用的
        val kafkaStream: ReceiverInputDStream[(String, String)] =
            KafkaUtils.createStream[String, String, StringDecoder, StringDecoder]( //对key进行编码 StringDecoder
                //参数
                streamingContext,
                kafkaParmeters,
                topicsMap,
                StorageLevel.MEMORY_AND_DISK_SER
            )
        //kafkaSream从kafka取到的数据是key-value对，不是一行一行的。 一个批处理时间段内消费的数据
        val valueDS: DStream[String] = kafkaStream.map {
            //(String, String)
            //(key,value) key判断分区用的 ，我们只关心value
            case (key, value) => {
                value
            }
        }
        valueDS.print()


        /*
         val wordToCountDStream: DStream[(String, Int)] = kafkaDStream.map {
          case (k, v) => {
            (v,1)
          }
        }

        // reduceByKey
        val wordToSumDStream: DStream[(String, Int)] = wordToCountDStream.reduceByKey(_+_)

        // 打印数据
        wordToSumDStream.print()
        */

        // TODO 启动采集器
        streamingContext.start()

        // TODO Driver不能停止，等待采集器的结束
        // wait, sleep
        streamingContext.awaitTermination()

    }
}
