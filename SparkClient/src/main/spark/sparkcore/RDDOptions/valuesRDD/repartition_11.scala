package sparkcore.RDDOptions.valuesRDD

//TODO 作用：根据分区数，重新通过网络随机洗牌所有数据。
object repartition_11 extends SC{

    def main(args: Array[String]): Unit = {
        test01
    }
    // 语法 TODO 需求：创建一个4个分区的RDD，对其重新分区
    def test01: Unit ={
        val rdd = sc.parallelize(1 to 16,4)

        //查看RDD的分区数
//        println("partitions = " + rdd.partitions.size)  //partitions = 4

        //对RDD重新分区
        //TODO  底层调用的是 coalesce(numPartitions, shuffle = true) ，经过shuffle打散重组分区元素的过程
        val rerdd = rdd.repartition(2)

        //查看新RDD的分区数
        println("partitions = " + rerdd.partitions.size)  //partitions = 2
    }
}
