package sparkSql.dataFrame.createDataFrame

import org.apache.spark.sql.types.{StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 在Spark SQL中SparkSession是创建DataFrame和执行SQL的入口，创建DataFrame有三种方式：
  * 通过Spark的数据源进行创建；从一个存在的RDD进行转换；还可以从Hive Table进行查询返回。
  *
  * （1）查看Spark数据源进行创建的文件格式
  * scala> spark.read.
  * csv   format   jdbc   json   load   option   options   orc   parquet   schema   table   text   textFile
  */
//1）从Spark数据源进行创建
object SparkRead {
//    val sparkConf = new SparkConf()
//    sparkConf.setAppName("wordCount")
//    sparkConf.setMaster("local[2]");
    //2.获取spark的上下文环境  spark-core的核心入口
//    private val sc = new SparkContext(sparkConf)

    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()



    /**
      * TODO 1. 读取json文件，创建DF
      * 文件内容
      * {"name":"zhangsan","age":"30"}
      * {"name":"zhangsan2","age":"20"}
      * {"name":"zhangsan3","age":"11"}
      * {"name":"zhangsan4","age":"62"}
      */
    def sparkRead_json: Unit ={
        //1. 读取json文件，创建DF
        val jsonPath = "D:\\hadoop\\sparkSql\\sparkRead\\peopleJson.txt"
        val jsonDataFrame: DataFrame = spark.read.json(jsonPath)
        jsonDataFrame.show();
    }

    def main(args: Array[String]): Unit = {

    }
}
