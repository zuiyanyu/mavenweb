package sparkcore.RDDOptions.keyValueRDD

//TODO 1. 作用：在类型为(K,V)和(K,W)的RDD上调用，返回一个(K,(Iterable<V>,Iterable<W>))类型的RDD
object cogroup_11 extends SC{

    //TODO 2. 需求：创建两个pairRDD，并将key相同的数据聚合到一个迭代器。
    def main(args: Array[String]): Unit = {
        val rdd1 = sc.parallelize(Array((1,"a"),(2,"b"),(2,"d"),(3,"c")))
        val rdd2 = sc.parallelize(Array((1,4),(2,5),(3,6),(3,7)))

        //[ (2,(CompactBuffer(b, d),CompactBuffer(5))),(1,(CompactBuffer(a),CompactBuffer(4))),(3,(CompactBuffer(c),CompactBuffer(6, 7))) ]
        listPrint(rdd1.cogroup(rdd2).collect())

        //[ (2,(CompactBuffer(5),CompactBuffer(b, d))),(1,(CompactBuffer(4),CompactBuffer(a))),(3,(CompactBuffer(6, 7),CompactBuffer(c))) ]
        listPrint(rdd2.cogroup(rdd1).collect())


        /**
          * [(1,a),(2,b)]
          * [(2,d),(3,c)]
          */
        glomPrint(rdd1 )

        /**
          * [(1,4),(2,5)]
          * [(3,6),(3,7)]
          */
        glomPrint(rdd2)

        //看下结果分区情况
        /**
          * [(2,(CompactBuffer(b, d),CompactBuffer(5)))]
          * [(1,(CompactBuffer(a),CompactBuffer(4))),(3,(CompactBuffer(c),CompactBuffer(6, 7)))]
          */
        glomPrint(rdd1.cogroup(rdd2))

        /**
          * [(2,(CompactBuffer(5),CompactBuffer(b, d)))]
          * [(1,(CompactBuffer(4),CompactBuffer(a))),(3,(CompactBuffer(6, 7),CompactBuffer(c)))]
          */
        glomPrint(rdd2.cogroup(rdd1))

    }

}
