package kafka.producer_consumer.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InterceptorProducer {
    public static void main(String[] args) throws Exception {
        // 1 设置配置信息  ProducerConfig 中都能找到
        Properties props = new Properties();
        props.put("bootstrap.servers", "hadoop102:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all"); // asks = all 等价于 asks = -1
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 2 构建拦截链
        List<String> interceptors = new ArrayList<>();
        interceptors.add("kafka.producer_consumer.producer.interceptor.TimeInterceptor");
        interceptors.add("kafka.producer_consumer.producer.interceptor.CounterInterceptor");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        String topic = "kafka-topic";
        Producer<String, String> producer = new KafkaProducer<>(props);

        // 3 发送消息
        for (int i = 0; i < 1; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, "message" + i);
            producer.send(record);
        }

        // 4 一定要关闭producer，这样才会调用interceptor的close方法
        producer.close();
    }
}
