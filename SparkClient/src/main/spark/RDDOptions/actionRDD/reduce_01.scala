package RDDOptions.actionRDD

//TODO 作用：通过func函数聚集RDD中的所有元素，先聚合分区内数据，再聚合分区间数据。
object reduce_01 extends SC{
    //TODO 需求：创建一个RDD，将所有元素聚合得到结果。
    def main(args: Array[String]): Unit = {
        val rdd1 = sc.makeRDD(1 to 10,2)

        //聚合RDD[Int]所有元素
        val i: Int = rdd1.reduce(_+_)
        println(i) // 55

        val rdd2 = sc.makeRDD(Array(("a",1),("a",3),("c",3),("d",5)))
        //聚合RDD[String]所有数据
        val tuple: (String, Int) = rdd2.reduce((x,y)=>(x._1 + y._1,x._2 + y._2))
        println(tuple) //(aacd,12)
    }
}
