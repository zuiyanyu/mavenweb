package RDDOptions.keyValueRDD

import org.apache.spark.rdd.RDD

/**
  * def aggregate[U: ClassTag](zeroValue: U)(seqOp: (U, T) => U, combOp: (U, U) => U): U
  *
  * zeroValue
  */
object aggregateByKey_05 extends SC{
    //TODO  需求：创建一个pairRDD，取出每个分区相同key对应值的最大值，然后相加
    //分析：必须先进行区内聚合，然后再进行区间聚合
    def main(args: Array[String]): Unit = {
        test02

    }
    def test02: Unit ={

        val rdd = sc.parallelize(List(("a",3),("a",2),("c",4),("b",3),("c",6),("c",8)),2)
        /*
        [(a,3),(a,2),(c,4)]
        [(b,3),(c,6),(c,8)]
         */
        glomPrint(rdd)
        val result: RDD[(String, Int)] = rdd.aggregateByKey(1)(_+_,_+_)

        /*
        TODO 1.分区内的每个key都会累加一次零值
           zeroValue = 0:
            [(b,3)]
            [(a,5),(c,18)]

           zeroValue = 1:
            [(b,4)]
            [(a,6),(c,20)]
         */
        glomPrint(result)

        /*
         TODO 2.分区间的值聚合不会再累加零值
        zeroValue = 0: [ (b,3),(a,5),(c,18) ]
        zeroValue = 1:[ (b,4),(a,6),(c,20) ]
         */
        listPrint(result.collect())
    }
    def test01: Unit ={
        val rdd = sc.parallelize(List(("a",3),("a",2),("c",4),("b",3),("c",6),("c",8)),2)
        glomPrint(rdd)
        /**
          * [(a,3),(a,2),(c,4)]
          * [(b,3),(c,6),(c,8)]
          */

        //取出每个分区相同key对应值的最大值，然后相加
        val result: RDD[(String, Int)] = rdd.aggregateByKey(0)((initValue,value)=>Math.max(initValue,value),(first,second)=>first + second)
        listPrint(result.collect()) // [ (b,3),(a,3),(c,12) ]


    }

}
