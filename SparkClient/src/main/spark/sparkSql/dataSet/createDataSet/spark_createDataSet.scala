package sparkSql.dataSet.createDataSet

import org.apache.hadoop.io.{LongWritable, Text}
import org.apache.hadoop.mapred.TextInputFormat
import org.apache.spark.rdd.RDD
import org.apache.spark.sql._

object spark_createDataSet {
    val spark:SparkSession = SparkSession.builder().appName("column").master("local[2]").getOrCreate()
    import spark.implicits._

    private val person_with_header  = "D:\\hadoop\\sparkSql\\csvData\\person_with_header.csv"
    def rddToDataSet(): Unit ={
        val charset = "GBK" //"UTF-8"
        val rdd: RDD[String] = spark.sparkContext.hadoopFile[LongWritable, Text, TextInputFormat](person_with_header)
                .mapPartitions(_.map(pair => new String(pair._2.getBytes, 0, pair._2.getBytes.length,charset )))
        val ds: Dataset[String] = spark.createDataset(rdd)(Encoders.STRING)
        ds.show()

        /**charset = "UTF-8"
          * +-------------+
          * |        value|
          * +-------------+
          * |  id,name,age|
          * |1, darren ,18|
          * |2,\N,18en ,18|
          * |  3,"test",18|
          * | 4,'test2',18|
          * | 4,'test2',18|
          * +-------------+
          */
    }
    def jsonTODataSet: Unit ={
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
    case class Person(name: String, age: Long)
    def caseClassToDS: Unit ={
        val persons = Seq(Person("zhangsan", 32),Person("lisi", 34))
        //TODO 方式2：使用sparkSession对象
        val ds: Dataset[Person] = spark.createDataset(persons)
        ds.show()
    }
    def main(args: Array[String]): Unit = {
        caseClassToDS
    }
}
