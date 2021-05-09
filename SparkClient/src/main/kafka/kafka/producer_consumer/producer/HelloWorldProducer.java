package kafka.producer_consumer.producer;

import java.util.Properties;
import java.util.Random;

/**
 *
 *生产者：
 * bin/kafka-console-producer.sh --broker-list hadoop102:9092,hadoop103:9092,hadoop104:9092 --topic kafka-topic
 *
 * 消费者：
 * bin/kafka-console-consumer.sh --bootstrap-server hadoop102:9092,hadoop103:9092,hadoop104:9092 --from-beginning --topic kafka-topic
 */
public class HelloWorldProducer {
    public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        Random rnd = new Random();

        Properties props = new Properties();
//        props.put("bootstrap.servers", "hadoop103:9092,hadoop103:9093,hadoop103:9094");
        props.put("bootstrap.servers", "hadoop103:9092,hadoop103:9093,hadoop103:9094");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    }
}
