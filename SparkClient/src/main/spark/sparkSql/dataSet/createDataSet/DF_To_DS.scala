package sparkSql.dataSet.createDataSet

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import sparkSql.dataSet.createDataSet.RDD_To_DS.sc

object DF_To_DS {
    private val spark: SparkSession = SparkSession.builder().appName("RDD_To_DS").master("local[2]").getOrCreate()
    private val sc: SparkContext = spark.sparkContext
    import spark.implicits._

    //TODO  获取RDD
    def getRDD  = {
        sc.makeRDD(
            Array(
                ("zhangsan",20),
                ("zhangsan",20),
                ("lisi",21),
                ("wangwu",22)
            )
        )
    }

    //TODO  获取DF
    case class Person(name: String, age: Long)
    def getDF ={
        //获取rdd数据，并将元素转换为样例类
        val rdd: RDD[(String, Int)] = getRDD
        val personRDD: RDD[Person] = rdd.map{case (name,age)=>Person(name,age)}

        val df: DataFrame = personRDD.toDF()
        df
    }

    //TODO dataFrame 转换为 DataSet ，需要利用样例类
    def DF_TO_DS: Unit ={
        val df: DataFrame = getDF

        //TODO dataFrame 转换为 DataSet
        //def as[U : Encoder]: Dataset[U] = Dataset[U](sparkSession, logicalPlan)
        val ds: Dataset[Person] = df.as[Person]
        ds.show()

        //TODO dataSet 转换为 DataFrame
        val dataFrame: DataFrame = ds.toDF()


    }

    def main(args: Array[String]): Unit = {
        DF_TO_DS
    }
}
