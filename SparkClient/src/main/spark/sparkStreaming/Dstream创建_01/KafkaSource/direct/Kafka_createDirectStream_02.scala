package sparkStreaming.Dstream创建_01.KafkaSource.direct

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.KafkaCluster.Err
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

object Kafka_createDirectStream_02 {
    def main(args: Array[String]): Unit = {
        //1. 创建SparkConf对象
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("KafkaStreaming")

        //2. 创建StreamingContext对象
        val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(3))

        //kafka参数声明
        val brokers = "hadoop102:9092,hadoop103:9092,hadoop104:9092"
        val topic = "kafka_topic"
        val group = "kafkaConsumerGroup01"
        //Deserializer class for key that implements the <code>Deserializer</code> interface.
        val deserialization = "org.apache.kafka.common.serialization.StringDeserializer"

        //定义Kafka参数
        val kafkaParameters: Map[String, String] = Map[String, String](
            ConsumerConfig.GROUP_ID_CONFIG -> group,
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> deserialization,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> deserialization
        )

        //创建KafkaCluster（维护offset）
        val kafkaCluster = new KafkaCluster(kafkaParameters)

        //TODO 获取ZK中保存的offset  获取上次消费到的offset信息。
        val fromOffset: Map[TopicAndPartition, Long] = getOffsetFromZookeeper(kafkaCluster, group, Set(topic))

        //读取kafka数据创建DStream
        // createDirectStream[
        //    K: ClassTag,                   type of Kafka message key
        //    V: ClassTag,                   type of Kafka message value
        //    KD <: Decoder[K]: ClassTag,    type of Kafka message key decoder
        //    VD <: Decoder[V]: ClassTag,    type of Kafka message value decoder
        //    R: ClassTag]                   type returned by messageHandler
        // 方法返回结果：DStream of R   存储R类型的DStream
        val kafkaDStream: InputDStream[String] =
        KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, String](
            ssc,
            kafkaParameters,
            fromOffset,
            //messageHandler: MessageAndMetadata[K, V] => R   , x.message()返回的是value值
            //读取topic的分区下的一个 信息的key值和value值封装成一个 MessageAndMetadata对象传递给这个函数。
            (x: MessageAndMetadata[String, String]) => x.message()
        )

        //TODO 数据处理   会触发kafka分区的数据被拉取到 KafkaRDD的分区汇中。 rdd分区存储的topic分区偏移量信息会被更新。
        kafkaDStream.print

        //TODO 提交offset   提交被更新的最新 offset信息
        offsetToZookeeper(kafkaDStream, kafkaCluster, group)

        ssc.start()
        ssc.awaitTermination()
    }

    /**
      * private[kafka] def getFromOffsets(
      * kc: KafkaCluster,
      * kafkaParams: Map[String, String],
      * topics: Set[String]
      * ): Map[TopicAndPartition, Long] = {
      * val reset = kafkaParams.get("auto.offset.reset").map(_.toLowerCase(Locale.ROOT))
      * val result = for {
      * topicPartitions <- kc.getPartitions(topics).right
      * leaderOffsets <- (if (reset == Some("smallest")) {
      *         kc.getEarliestLeaderOffsets(topicPartitions)
      * } else {
      *         kc.getLatestLeaderOffsets(topicPartitions)
      * }).right
      * } yield {
      *       leaderOffsets.map { case (tp, lo) =>
      * (tp, lo.offset)
      * }
      * }
      *     KafkaCluster.checkErrors(result)
      * }
      *
      * @param kafkaCluster
      * @param kafkaGroup
      * @param kafkaTopicSet
      * @return
      */
    //从ZK获取offset
    def getOffsetFromZookeeper(kafkaCluster: KafkaCluster, kafkaGroup: String, kafkaTopicSet: Set[String]): Map[TopicAndPartition, Long] = {

        // 创建Map存储Topic和分区对应的offset
        val topicPartitionOffsetMap = new mutable.HashMap[TopicAndPartition, Long]()

        // 获取传入的Topic的所有分区
        // Either[Err, Set[TopicAndPartition]]  : Left(Err)   Right[Set[TopicAndPartition]]
        val topicAndPartitions: Either[Err, Set[TopicAndPartition]] = kafkaCluster.getPartitions(kafkaTopicSet)

        // 如果成功获取到Topic所有分区
        // topicAndPartitions: Set[TopicAndPartition]
        if (topicAndPartitions.isRight) {
            // 获取分区
            // partitions: Set[TopicAndPartition]
            val partitions: Set[TopicAndPartition] = topicAndPartitions.right.get

            // 获取指定分区的offset
            // offsetInfo: Either[Err, Map[TopicAndPartition, Long]]
            // Left[Err]  Right[Map[TopicAndPartition, Long]]
            val offsetInfo: Either[Err, Map[TopicAndPartition, Long]] = kafkaCluster.getConsumerOffsets(kafkaGroup, partitions)

            if (offsetInfo.isLeft) {

                // 如果没有offset信息则存储0
                // partitions: Set[TopicAndPartition]
                for (topicAndPartition <- partitions)
                // topicPartitionOffsetMap : mutable.HashMap[TopicAndPartition, Long]()
                    topicPartitionOffsetMap += (topicAndPartition -> 0L)
            } else {

                // 如果有offset信息则存储offset
                // offsets: Map[TopicAndPartition, Long]
                val offsets: Map[TopicAndPartition, Long] = offsetInfo.right.get
                for ((topicAndPartition, offset) <- offsets)
                    topicPartitionOffsetMap += (topicAndPartition -> offset)
            }
        }
        topicPartitionOffsetMap.toMap
    }

    //提交offset
    def offsetToZookeeper(kafkaDstream: InputDStream[String], kafkaCluster: KafkaCluster, kafka_group: String): Unit = {
        kafkaDstream.foreachRDD {
            // TODO 此处的rdd是 KafkaRDD ，一个InputDStream对应一个KafkaRdd， 一个topic分区对应一个KafkaRdd的分区。
            // TODO Rdd的分区没有存储 kafka分区数据，只是存储着每个kafka分区的最新元数据信息。只有遇到行动算子才会加载数据，这里的遍历不会触发拉取数据的动作。
            rdd =>

                // TODO 获取DStream中的offset信息   每个rdd分区的offsetRange， 即每个topic分区的offsetRange
                // offsetsList: Array[OffsetRange]
                // OffsetRange: topic partition fromoffset untiloffset
                //class  KafkaRDD  extends RDD[R](sc, Nil) with Logging with HasOffsetRanges
                val offsetRangList: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges

                // TODO 遍历每一个offset信息，即遍历topic每个分区的offset信息， 并更新Zookeeper中的元数据
                // OffsetRange: topic partition fromoffset untiloffset
                for (offsetRang <- offsetRangList) {
                    val topicAndPartition = TopicAndPartition(offsetRang.topic, offsetRang.partition)
                    // ack: Either[Err, Map[TopicAndPartition, Short]]
                    // Left[Err]
                    // Right[Map[TopicAndPartition, Short]]
                    val ack: Either[Err, Map[TopicAndPartition, Short]] = kafkaCluster.setConsumerOffsets(kafka_group, Map((topicAndPartition, offsetRang.untilOffset)))
                    if (ack.isLeft) {
                        println(s"Error updating the offset to Kafka cluster: ${ack.left.get}")
                    } else {
                        println(s"update the offset to Kafka cluster: ${offsetRang.untilOffset} successfully")
                    }
                }
        }
    }
}
