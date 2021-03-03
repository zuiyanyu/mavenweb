package sparkSql.dataSet.createDataSet

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}

object RDD_To_DS {
    private val spark: SparkSession = SparkSession.builder().appName("RDD_To_DS").master("local[2]").getOrCreate()
    private val sc: SparkContext = spark.sparkContext
    import spark.implicits._

    def main(args: Array[String]): Unit = {
        rdd_to_ds
    }


    def getRDD  = {
        sc.makeRDD(Array(("zhangsan",20),("lisi",21),("wangwu",22)))
    }

    //TODO 如果rdd的存储元素是样例类，直接可以转换为DataSet
    case class Person(name: String, age: Long)
    def rdd_to_ds: Unit ={
        //获取rdd数据，并将元素转换为样例类
        val rdd: RDD[(String, Int)] = getRDD
        val personRDD: RDD[Person] = rdd.map{case (name,age)=>Person(name,age)}

        //如果rdd的存储元素是样例类，直接可以转换为DataSet
        val ds: Dataset[Person] = personRDD.toDS
        ds.show()

        // TODO ds 转换为 rdd
        val ds_to_rdd: RDD[Person] = ds.rdd
    }

}
