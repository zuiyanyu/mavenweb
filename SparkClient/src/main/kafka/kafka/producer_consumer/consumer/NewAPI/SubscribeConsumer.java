package kafka.producer_consumer.consumer.NewAPI;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

//TODO 消费者订阅的topic, 可同时订阅多个
public class SubscribeConsumer {

    @Test
    public void Consumer_Subscribe_Test() {
        Properties props = new Properties();
        // Kafka服务端的主机名和端口号
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hadoop102:9092,hadoop102:9092,hadoop103:9092");//,hadoop102:9092,hadoop103:9092
        // 等待所有副本节点的应答
        props.put(ProducerConfig.ACKS_CONFIG, "all");//"acks"

        /**
         * 如果auto.commit.enable=true，当consumer fetch了一些数据但还没有完全处理掉的时候，刚好到commit interval出发了提交offset操作，
         * 接着consumer crash掉了。这时已经fetch的数据还没有处理完成但已经被commit掉，因此没有机会再次被处理，数据丢失
         */
        // 制定consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        // 是否自动确认offset
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动确认offset的时间间隔
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        // key的序列化类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        // 定义consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // 消费者订阅的topic, 可同时订阅多个
        consumer.subscribe(Arrays.asList("kafka-topic"));
        //consumer.seek(new TopicPartition("kafka-topic",0),10);
        final int minBatchSize = 200;
//        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//                buffer.add(record);
            }
//            if (buffer.size() >= minBatchSize) {
            //将数据保存到数据库
//                insertIntoDb(buffer);
//                consumer.commitSync();
//                buffer.clear();
//            }
        }
    }

}
