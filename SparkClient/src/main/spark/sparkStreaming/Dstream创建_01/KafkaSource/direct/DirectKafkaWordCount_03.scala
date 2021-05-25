//package sparkStreaming.Dstream创建_01.KafkaSource.direct
//
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.kafka.KafkaUtils
//import org.apache.spark.streaming.{Seconds, StreamingContext}
//
///**
//  * 这里的例子是Spark源码example中的例子，主要实现的是拉取Kafka数据，并计算work count的过程。
//  */
//class DirectKafkaWordCount_03 {
//    def main(args: Array[String]) {
//        if (args.length < 2) {
//            System.err.println(s"""
//                                  |Usage: DirectKafkaWordCount <brokers> <topics>
//                                  |  <brokers> is a list of one or more Kafka brokers
//                                  |  <topics> is a list of one or more kafka topics to consume from
//                                  |
//        """.stripMargin)
//            System.exit(1)
//        }
//
//        StreamingExamples.setStreamingLogLevels()
//
//        val Array(brokers, topics) = args
//
//        // Create context with 2 second batch interval
//        val sparkConf = new SparkConf().setAppName("DirectKafkaWordCount")
//        val ssc = new StreamingContext(sparkConf, Seconds(2))
//
//        // Create direct kafka stream with brokers and topics
//        val topicsSet = topics.split(",").toSet
//        val kafkaParams = Map[String, String]("metadata.broker.list" -> brokers)
//        val messages = KafkaUtils.createDirectStream[String, String](
//            ssc,
//            LocationStrategies.PreferConsistent,
//            ConsumerStrategies.Subscribe[String, String](topicsSet, kafkaParams))
//
//        // Get the lines, split them into words, count the words and print
//        val lines = messages.map(_.value)
//        val words = lines.flatMap(_.split(" "))
//        val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
//        wordCounts.print()
//
//        // Start the computation
//        ssc.start()
//        ssc.awaitTermination()
//    }
//}
