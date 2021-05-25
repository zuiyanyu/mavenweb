package sparkStreaming.Dstream创建_01.KafkaSource.receiver

import kafka.serializer.{DefaultDecoder, StringDecoder}
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * 1.  topics 参数的作用
  * 在
  * val kafkaStream = KafkaUtils.createStream(
  * streamingContext,
  * [ZK quorum],
  * [consumer group id],
  * [per-topic number of Kafka partitions to consume]指定每次并行消费某个主题几个分区内的数据
  * )
  *
  * 在kafkatils.createStream将创建一个接收器并使用Kafka主题。
  * 选项“每个主题要使用的Kafka分区数”表示这个接收器将并行读取多少个分区。
  * 例如：
  * 您有一个名为“Topic1”的主题，其中有两个分区，并且您提供了选项“Topic1”：1，那么Kafka接收器将一次读取一个分区[它最终将读取所有分区，但每次将读取一个分区]。
  * 这样做的原因是在分区中读取消息，并保留数据写入主题的顺序。
  * 例如 ，Topic1的partition1包含消息{1,11,21,31,41}，partition2包含消息{2,12,22,32,42}，那么使用上述设置(“Topic1”：1)进行读取将生成一个流，如{1,11,21,31,41,2,12,22,32,42}。每个分区中的消息是分开读取的，因此它不会混合在一起。
  *
  * 如果您以'Topic1'：2提供选项，则接收器将一次读取2个分区，并且这些分区内的消息将混合在一起。
  * 对于上面开始的同一个示例，具有“Topic1”：2的接收器将生成类似于{1,2,11,12,21,22….}
  * 可以将其视为接收器可以对给定主题分区执行的并行读取数。
  *
  * 2. 一个流中是否可以指定多个主题？ 是的，可以.
  */
object Receiver_based_Approach_02 {
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setAppName("etlStreamReceiverExtract").setMaster("local[*]")
        val ssc = new StreamingContext(conf,Seconds(20));

        //TODO 如果启动wal的话需要设置checkpoint目录 保存到hdfs目录上
        val ckPath = "/sparkStreaming/checkpoint"
        ssc.checkpoint(ckPath)

        //kafka参数声明
        val zkQuorum = "hadoop102:2181,hadoop103:2181,hadoop104:2181"
        val topic = "kafka_topic"
        val group = "kafka_topic_Group01"
        //Deserializer class for key that implements the <code>Deserializer</code> interface.
        val deserialization = "org.apache.kafka.common.serialization.StringDeserializer"

        //定义Kafka参数
        // KafkaInputDstream的onStart()中会用到 "zookeeper.connect"，进行Kafka的连接。
        val kafkaParameters: Map[String, String] = Map[String, String](
            ConsumerConfig.GROUP_ID_CONFIG -> group,
            "zookeeper.connect" -> zkQuorum,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> deserialization,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> deserialization
        )

        // 主题有两个分区， 启动的ReceiverInputDStream的receive开启一个线程(即一个消费者)来消费主题分区。
        val topics = "kafka_topic:2:1"
        val topicInfo = topics.split(",").map{
            line =>
                val arr = line.split(":")
                val topic = arr(0)
                val partition = arr(1).toInt  //此主题下有几个分区
                val threads = arr(2).toInt    //开启多线程来并行读取分区数据
                (topic,partition,threads)
        }

        //创建根Kafka分区数一样的dstream
        topicInfo.foreach{
            case (topic,partition,threads) =>
                val topicMap = Map(topic -> threads) //一次并行消费此主题下的多个分区内的数据。多线程读取消费实现。
                //每个topic分区都会对应一个dstream，对应一个receiver进行监控，消费kafka分区内的新数据。
                val streams = (1 to partition).map(
                    //TODO 可以使用多个 Receiver 来消费同一个 topic，降低每个 receiver 接收的数据量
                    //2个分区 启动2个 InputDStream，每个InputDstream启动一个Receiver，数据会到两个executor上被处理。
                    //每个Receiver启动1个消费者，共启动2个消费者 ； 主题的2个分区负载均衡地被2个消费者消费。
                    t=>KafkaUtils.createStream[String,Array[Byte],StringDecoder,DefaultDecoder](ssc,kafkaParameters,topicMap,StorageLevel.MEMORY_AND_DISK_SER)
                )
                //将每个dStream批处理时间内的数据 聚合到
                ssc.union(streams)
        }





































































    }
}
