package sparkcore.RDDOptions.keyValueRDD

import org.apache.spark.rdd.RDD

//TODO  作用：在一个(K,V)的RDD上调用，K必须实现Ordered接口，返回一个按照key进行排序的(K,V)的RDD
object sortByKey_08 extends SC{
    //TODO  需求：创建一个pairRDD，按照key的正序和倒序进行排序
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array((3,"aa"),(6,"cc"),(2,"bb"),(1,"dd")))

        //true，就是根据key升序排序
        val sortRDD: RDD[(Int, String)] = rdd.sortByKey(true)
        listPrint(sortRDD.collect()) // [ (1,dd),(2,bb),(3,aa),(6,cc) ]
    }

}
