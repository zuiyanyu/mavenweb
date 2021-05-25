package sparkcore.RDDOptions.valuesRDD

//TODO 作用：缩减分区数，用于大数据集过滤后，提高小数据集的执行效率。
object coalesce_10 extends SC{
    def main(args: Array[String]): Unit = {
        test01
    }

    //01 语法
    //TODO 需求：创建一个4个分区的RDD，对其缩减分区
    def test01: Unit ={
        val rdd = sc.parallelize(1 to 16,4)

        //查看RDD的分区数
        var partitionSize: Int = rdd.partitions.size
        println("partitionSize = " + partitionSize) // partitionSize = 4

        //对RDD重新分区  第二个参数控制重分区是否要进行shuffle重组
        val coalesceRDD = rdd.coalesce(3,false)

        //查看新RDD的分区数
        partitionSize = coalesceRDD.partitions.size
        println("partitionSize2 = " + partitionSize) // partitionSize2 = 3
    }
}
