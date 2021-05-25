package sparkcore.RDD缓存

import sparkcore.SC.SC
import org.apache.spark.rdd.RDD

//TODO 缓存，是将数据暂时缓存在内存中，数据可能会丢失(内参满了会清空)，也不安全（断电丢失）
object Cache_Register_01 extends SC{
    def main(args: Array[String]): Unit = {
        test02
    }
    //演示缓存的效果
    def test02(): Unit ={
        //（1）创建一个RDD
        val rdd = sc.makeRDD(Array("spark"))

        //（2）将RDD转换为携带当前时间戳不做缓存
        val nocache = rdd.map(_.toString+System.currentTimeMillis)

        //（3）多次打印结果
        println("1:"+ nocache.collect.mkString(","))
        println("2:"+  nocache.collect.mkString(","))
        println("3:"+  nocache.collect.mkString(","))

        //（4）将RDD转换为携带当前时间戳并做缓存
        val cache =  rdd.map(_.toString+System.currentTimeMillis).cache()

        //（5）多次打印做了缓存的结果
        println("4:"+  cache.collect.mkString(","))
        println("5:"+  cache.collect.mkString(","))
        println("6:"+  cache.collect.mkString(","))

        /**
          * 1:spark1613216681868
          * 2:spark1613216681896
          * 3:spark1613216681909
          *
          * 4:spark1613216681934
          * 5:spark1613216681934
          * 6:spark1613216681934
          */

    }
    //TODO 01 缓存对血缘的影响
    def test01(): Unit ={
        val rdd = sc.makeRDD(Array(1,2,3,4))
        val mapRDD: RDD[(Int, Int)] = rdd.map((_,1))

        //TODO cache缓存   只有在行动算子使用后才会进行缓存
        mapRDD.cache()

        val reduceRDD: RDD[(Int, Int)] = mapRDD.reduceByKey(_+_)


        //执行行动算子之前：获取reduceRDD的血缘关系
        val reduceRDD_LineAge1: String = reduceRDD.toDebugString
        println("reduceRDD的血缘关系:")
        println(reduceRDD_LineAge1)
        /**
          * reduceRDD的血缘关系:
          * (2) ShuffledRDD[2] at reduceByKey at Cache_Register_01.scala:13 []
          *     +-(2) MapPartitionsRDD[1] at map at Cache_Register_01.scala:8 []
          *         |  ParallelCollectionRDD[0] at makeRDD at Cache_Register_01.scala:7 []
          */
        println("===============================")

        reduceRDD.collect();
        //执行行动算子之后：获取reduceRDD的血缘关系
        val reduceRDD_LineAge2: String = reduceRDD.toDebugString
        println("reduceRDD的血缘关系:")
        println(reduceRDD_LineAge2)
        /**
          * reduceRDD的血缘关系:
          * (2) ShuffledRDD[2] at reduceByKey at Cache_Register_01.scala:13 []
          *    +-(2) MapPartitionsRDD[1] at map at Cache_Register_01.scala:8 []
          * TODO |      CachedPartitions: 2; MemorySize: 176.0 B; ExternalBlockStoreSize: 0.0 B; DiskSize: 0.0 B
          *      |  ParallelCollectionRDD[0] at makeRDD at Cache_Register_01.scala:7 []
          */
    }
}
