package kafka.producer_consumer.consumer.lower_API.consumer_partition_offset;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.cluster.Broker;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * kafka-topic 这个主题的分区情况：
 * $ kafka-topics.sh --describe --zookeeper hadoop102:2181 --topic kafka-topic
 * Topic:kafka-topic       PartitionCount:3        ReplicationFactor:3     Configs:
 *         Topic: kafka-topic      Partition: 0    Leader: 2       Replicas: 2,3,4 Isr: 4,2,3
 *         Topic: kafka-topic      Partition: 1    Leader: 3       Replicas: 3,4,2 Isr: 2,4,3
 *         Topic: kafka-topic      Partition: 2    Leader: 4       Replicas: 4,3,2 Isr: 4,3,2
 */
public class LowLevelConsumer {
    public static void main(String[] args) {
        ArrayList<String> brokers = new ArrayList<>();//kafka集群
        brokers.add("hadoop102");
        brokers.add("hadoop103");
        brokers.add("hadoop104");

        int port = 9092;//连接kafka集群的端口号
        String topic = "kafka-topic";//待消费的主题
        int partition = 1;//待消费的分区
        long offset = 0;//待消费的位置信息

        //两种方式，第一种性能好些
        getData(brokers, port, topic, partition, offset); //totalTime = 397,523,417, 377
//        getData_type2(brokers, port, topic, partition, offset); //totalTime = 375,540, 520,526, 510

    }
    //方式2：进行尝试获取 代码量少
    private static void getData_type2(ArrayList<String> brokers, int port, String topic, int partition, long offset) {
        long startTime = System.currentTimeMillis();
        FetchRequest fetchRequest = new FetchRequestBuilder()
                .addFetch(topic, partition, 0, 1024 * 1024 * 2)
                .build();

        for (String broker : brokers) {
            SimpleConsumer getLeader = new SimpleConsumer(broker, port, 2000, 1024 * 4, "getLeader");

            //5.2、发送抓取数据的请求并得到返回值
            FetchResponse fetchResponse = getLeader.fetch(fetchRequest);
            System.out.println(fetchResponse);

            System.out.println("====ByteBufferMessageSet");
            ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partition);
            System.out.println(messageAndOffsets);

            Iterator<MessageAndOffset> iterator = messageAndOffsets.iterator();
            while (iterator.hasNext()){
//                System.out.println(" broker = " + broker);
                MessageAndOffset  messageAndOffset = iterator.next();

                //提取每一条消息中的数据信息  （有效负载数据）
                //payload 现在处于读模式
                ByteBuffer payload = messageAndOffset.message().payload();

                //将数据写入byte数据
                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);

                //打印结果
                System.out.println(new String(bytes));

            }
            System.out.println("------------------------");

        }
        long endTime = System.currentTimeMillis();
        System.out.println("totalTime = " + (endTime - startTime));
    }

    //TODO 精准获取，代码量多些
    private static void getData(ArrayList<String> brokers, int port, String topic, int partition, long offset) {
        long startTime = System.currentTimeMillis();

        //获取待消费分区的Leader信息
//        BrokerEndPoint leader = getLeader(brokers, port, topic, partition);
        Broker leader = getLeader(brokers, port, topic, partition);

        if (leader == null) {
            System.out.println("未找到指定主题指定分区的Leader信息!!!");
            return;
        }
        System.out.println("leader.connectionString() = "+leader.connectionString()); // hadoop103:9092
        System.out.println("leader.host() = "+leader.host());// hadoop103
        System.out.println("leader.port() = "+leader.port());//9092
        System.out.println("leader.productPrefix() = "+leader.productPrefix());//Broker
        String leaderHost = leader.host();

        //4、根据leaderHost信息创建SimpleConsumer
        SimpleConsumer getData = new SimpleConsumer(leaderHost, port, 2000, 1024 * 1024 * 4, "getData");

        //5、发送抓取数据的请求
        //5.1、构建抓取数据的请求
        //此leader所在主机可能存在其他分区的leader，所以这里可以多次调用addFetch来指定多个topic
        FetchRequest fetchRequest = new FetchRequestBuilder().addFetch(topic, partition, offset, 1024 * 1024 * 2).build();

        //5.2、发送抓取数据的请求并得到返回值
        FetchResponse fetchResponse = getData.fetch(fetchRequest);

        //6、解析返回值（打印）
        //6.1、addFetch方法可以被循环调用，所以此处我们需要传入特定的主题和分区信息来获取具体的数据集
        //一个分区有多条消息，就会有多个MessageAndOffset
        ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partition);

        //6.2、对结果集进行迭代操作，逐条解析
        //一条消息 一个 MessageAndOffset
        for (MessageAndOffset messageAndOffset : messageAndOffsets) {
            System.out.println("000000000000000");
            //提取每一条消息中的数据信息  （有效负载数据）
            ByteBuffer payload = messageAndOffset.message().payload();

            //将数据写入byte数据
            byte[] bytes = new byte[payload.limit()];
            payload.get(bytes);

            //打印结果
            System.out.println(new String(bytes));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("totalTime = " + (endTime - startTime));
    }
    //根据集群、主题和分区信息获取待消费的Leader信息
    private static Broker getLeader(ArrayList<String> brokers, int port, String topic, int partition) {
        System.out.println("========================================");
         for (String broker : brokers) {

            //1、遍历集群，根据节点信息创建SimpleConsumer
            //获取哪一个kafka集群的消费者连接
            SimpleConsumer getLeader = new SimpleConsumer(broker, port, 2000, 1024 * 4, "getLeader");

            //2、发送元数据信息请求
            //2.1、根据传入的主题信息创建元数据请求
            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Collections.singletonList(topic));

            //2.2、发送元数据请求得到返回值
            // 为消费者指定要消费的主题
            TopicMetadataResponse metadataResponse = getLeader.send(topicMetadataRequest);

            //3、解析元数据返回值
            //3.1、创建请求传入的为Topic集合，返回值中包含多个Topic元数据信息
            //一个topic对应一个TopicMetadata
            List<TopicMetadata> topicsMetadata = metadataResponse.topicsMetadata();

            //3.2、遍历多个Topic的元数据信息
            for (TopicMetadata topicMetadata : topicsMetadata) {
                String topicName = topicMetadata.topic();
                System.out.println("topic-name = " + topicName );

                //一个Topic由多个Partition组成
                List<PartitionMetadata> partitionsMetadata = topicMetadata.partitionsMetadata();

                //遍历多个分区的元数据信息
                for (PartitionMetadata partitionMetadata : partitionsMetadata) {
                    //每个分区都有一个分区号，知名是topic的第几个分区
                    int partitionId = partitionMetadata.partitionId();
                    //匹配传入的分区号 判断这个分区是否是我们需要的分区，如果是，就获取此分区的副本leader
                    if (partition == partitionId) {
                        //匹配上则直接返回leader信息 ,leader 是一个broker节点
                        //我们要想消费某个分区的数据，必须找分区的副本leader进行请求消费
                        Broker leader = partitionMetadata.leader();
                        return leader;
                    }
                }
            }
        }
        return null;
    }



}
