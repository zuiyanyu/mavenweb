package sparkSql.load_write_data.load_data

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import sparkSql.dataSet.createDataSet.spark_createDataSet.spark

/**
  * 在Spark SQL中SparkSession是创建DataFrame和执行SQL的入口，创建DataFrame有三种方式：
  * 通过Spark的数据源进行创建；从一个存在的RDD进行转换；还可以从Hive Table进行查询返回。
  *
  * （1）查看Spark数据源进行创建的文件格式
  * scala> spark.read.
  * csv   format   jdbc   json   load   option   options   orc   parquet   schema   table   text   textFile
  */
//1）从Spark数据源进行创建
object SparkRead_json {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()

    private val sc: SparkContext = spark.sparkContext

    //TODO 1. 读取json文件，创建DF
    def sparkRead_json_file: Unit = {
        /**
          * 文件内容
          * {"name":"zhangsan","age":"30"}
          * {"name":"zhangsan2","age":"20"}
          * {"name":"zhangsan3","age":"11"}
          * {"name":"zhangsan4","age":"62"}
          */
        //1. 读取json文件，创建DF
        val jsonPath = "D:\\hadoop\\sparkSql\\sparkRead\\peopleJson.txt"
        val jsonDataFrame: DataFrame = spark.read.json(jsonPath)
        jsonDataFrame.show();
    }

    //读取 rdd中的json串
    def sparkRead_json_rdd: Unit = {
        val jsonRdd: RDD[String] = sc.makeRDD( """{"name":"Yin","address":{"city":"Columbus","state":"Ohio"}}""" :: Nil)

        val df: DataFrame = spark.read.json(jsonRdd)
        df.show()
        /*
        可以看到，嵌套的json就不能很好的解析出来了，成了一个数组
        +---------------+----+
        |        address|name|
        +---------------+----+
        |[Columbus,Ohio]| Yin|
        +---------------+----+
         */

        df.select("address").show

    }

    def main(args: Array[String]): Unit = {
        sparkRead_json_rdd
    }


}
