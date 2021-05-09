package kafka.producer_consumer.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PartitionerProducer {
    static Properties props = new Properties();

    static {
        // Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "hadoop103:9092");
        // 等待所有副本节点的应答
        props.put(ProducerConfig.ACKS_CONFIG, "all");//"acks"
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

        List<String> interceptors = new ArrayList<>();
        interceptors.add("kafka.producer_consumer.producer.interceptor.TimeInterceptor");
        interceptors.add("kafka.producer_consumer.producer.interceptor.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 自定义分区
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "kafka.producer_consumer.producer.partitioners.CustomPartitioner_newAPI");
//        props.put("advertised.listeners", "PLAINTEXT://hadoop103:9092");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String topic = "kafka-topic";


        Future<RecordMetadata> kafka = producer.send(new ProducerRecord<String, String>(topic, "2", "kafka"));
        kafka.get();
//        System.out.println("-----------1------");
//        producer.send(new ProducerRecord<String, String>(topic, "kafka2"));
//        System.out.println("-----------2------");
//        Future<RecordMetadata> partition_test = producer.send(new ProducerRecord<String, String>(topic, "partition_test"));

        System.out.println("-------3----------");

//        Thread.sleep(Integer.MAX_VALUE);
        producer.flush();
//        producer.close();
    }
}
