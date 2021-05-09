package kafka.producer_consumer.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class CallBackProducer {
    static Properties props = new Properties();
    static{
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "hadoop103:9092");
        // 等待所有副本节点的应答
        props.put("acks", "all");
        // 消息发送最大尝试次数
        props.put("retries", 0);
        // 一批消息处理大小
        props.put("batch.size", 16384);
        // 增加服务端请求延时
        props.put("linger.ms", 1);
        // 发送缓存区内存大小
        props.put("buffer.memory", 33554432);
        // key序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value序列化
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public static void main(String[] args) {

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        for (int i = 0; i < 2; i++) {

            kafkaProducer.send(new ProducerRecord<String, String>("kafka-topic", "hello" + i), new Callback() {

                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                    if (metadata != null) {

                        System.err.println(metadata.partition() + "---" + metadata.offset());
                    }
                }
            });
        }

        kafkaProducer.close();
    }
}
