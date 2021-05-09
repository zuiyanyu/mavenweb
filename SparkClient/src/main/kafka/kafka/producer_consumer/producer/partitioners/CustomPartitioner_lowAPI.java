package kafka.producer_consumer.producer.partitioners;


import kafka.producer.Partitioner;

public class CustomPartitioner_lowAPI implements Partitioner {
    //TODO 控制分区
    @Override
    public int partition(Object key, int numPartitions) {
        System.out.println("numPartitions = " + numPartitions);
        return 0;
    }
}
