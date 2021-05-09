package kafka.producer_consumer.producer.partitioners;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class CustomPartitioner_newAPI  implements Partitioner {
    // 控制分区
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        int partitionNum  ;
        if(null==key && null!=topic && topic.startsWith("kafka") ){
            partitionNum = 0;
        }else{
            Integer partitionCount = cluster.partitionCountForTopic(topic);
            System.out.println("partition Count for topic["+topic+"] = " + partitionCount);
            if(null != keyBytes){
                int hashCode = key.hashCode()& Integer.MAX_VALUE;
                System.out.println("get partitionNum by key HashCode ："+hashCode);
                partitionNum = hashCode% partitionCount;

            }else{
                int hashCode = value.hashCode()&Integer.MAX_VALUE;
                System.out.println("get partitionNum by value HashCode ："+hashCode);
                partitionNum= hashCode%partitionCount;
            }
        }
        System.out.println("key="+key +" ; value = " +value +"; partitionNum ="+partitionNum);
        return partitionNum ;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
