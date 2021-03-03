package sparkSql.dataSet.createDataSet

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object spark_createDataSet {
    val spark:SparkSession = SparkSession.builder().appName("column").master("local[2]").getOrCreate()
    import spark.implicits._

    def s: Unit ={
        val otherPeopleDataset: Dataset[String] = spark.createDataset(
            """{"name":"Yin","address":{"city":"Columbus","state":"Ohio"}}""" :: Nil)
        otherPeopleDataset.show()
        /*
        +--------------------+
        |               value|
        +--------------------+
        |{"name":"Yin","ad...|
        +--------------------+
         */

        val df: DataFrame = spark.read.json(otherPeopleDataset.rdd)
        df.show()

    }
    def main(args: Array[String]): Unit = {
        s
    }
}
