package sparkcore.RDDOptions.valuesRDD

import org.apache.spark.rdd.RDD

//TODO 作用；使用func先对数据进行处理，按照处理后的数据比较结果排序，默认为正序。
object sortBy_12 extends SC{
    def main(args: Array[String]): Unit = {
        test_01
    }
    //语法 TODO 需求：创建一个RDD，按照不同的规则进行排序
    def test_01: Unit ={
        val rdd = sc.parallelize(List(2,1,3,4))

        //按照自身大小排序
        val sort: RDD[Int] = rdd.sortBy(x => x)
        listPrint(sort.collect())

        //按照与3余数的大小排序
        val sort2: RDD[Int] = rdd.sortBy(x => x%3)
        listPrint(sort2.collect())

    }
}
