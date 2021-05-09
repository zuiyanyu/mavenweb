package kafka.producer_consumer.producer.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CounterInterceptor implements ProducerInterceptor<String, String> {
    private int errorCounter = 0;
    private int successCounter = 0;

    /**
     * 该方法封装进KafkaProducer.send方法中，即它运行在用户主线程中的。
     * Producer确保在消息被序列化以计算分区前调用该方法。
     * 用户可以在该方法中对消息做任何操作，但最好保证不要修改消息所属的topic和分区，否则会影响目标分区的计算
     * @param record
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 创建一个新的record，把时间戳写入消息体的最前部
        return record;
    }

    /**
     * 该方法会在消息被应答之前或消息发送失败时调用，并且通常都是在producer回调逻辑触发之前。
     * onAcknowledgement运行在producer的IO线程中，因此不要在该方法中放入很重的逻辑，否则会拖慢producer的消息发送效率
     * @param recordMetadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception exception) {

        // 统计成功和失败的次数
        if (exception == null) {
            successCounter++;
        } else {
            errorCounter++;
            System.out.println("保存异常原因 和异常记录 到数据库："+ exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * 关闭interceptor，主要用于执行一些资源清理工作
     */
    @Override
    public void close() {
        // 保存结果
        System.out.println("Successful sent: " + successCounter);
        System.out.println("Failed sent: " + errorCounter);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
