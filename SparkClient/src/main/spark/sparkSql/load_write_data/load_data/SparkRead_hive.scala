package sparkSql.load_write_data.load_data

import java.io.File

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * 测试本地可以连接hive
  * spark.sql("show databases").show
  * +------------+
  * |databaseName|
  * +------------+
  * |     default|
  * |       gmall|
  * |      hivedb|
  * +------------+
  */
object SparkRead_hive {
    def main(args: Array[String]): Unit = {
        //TODO 使用内置Hive需要指定一个Hive仓库地址。若使用的是外部Hive，则需要将hive-site.xml添加到ClassPath下。
        val warehouseLocation: String = new File("spark-warehouse").getAbsolutePath
        val config = new SparkConf()
        config.setMaster("local[*]").setAppName("Spark Hive Example")

        val spark = SparkSession
                .builder()
                .config(config)
                .config("spark.sql.warehouse.dir", warehouseLocation)
                .enableHiveSupport()
                .getOrCreate()

        spark.sql("show databases").show
        spark.sql("use hivedb")
        spark.sql("show tables").show
        spark.sql("select * from dept").show
    }
}
