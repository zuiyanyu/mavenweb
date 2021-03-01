package RDDOptions.keyValueRDD

//TODO 作用：在类型为(K,V)和(K,W)的RDD上调用，返回一个相同key对应的所有元素对在一起的(K,(V,W))的RDD
object join_10 extends SC{
    //TODO 需求：创建两个pairRDD，并将key相同的数据聚合到一个元组。
    def main(args: Array[String]): Unit = {
        val rdd1 = sc.parallelize(Array((1,"a"),(2,"b"),(2,"d"),(3,"c")))
        val rdd2 = sc.parallelize(Array((1,4),(2,5),(3,6),(3,7)))

        listPrint(rdd1.join(rdd2).collect()) //[ (2,(b,5)),(2,(d,5)),(1,(a,4)),(3,(c,6)),(3,(c,7)) ]

        listPrint(rdd2.join(rdd1).collect()) //[ (2,(5,b)),(2,(5,d)),(1,(4,a)),(3,(6,c)),(3,(7,c)) ]
    }
}
