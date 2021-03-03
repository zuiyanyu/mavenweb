package sparkSql.load_write_data.write_data

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import sparkSql.load_write_data.load_data.SparkRead_jdbc._

object df_write_jdbc {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()

    private val sc: SparkContext = spark.sparkContext
    import spark.implicits._


    val url = "jdbc:mysql://localhost:3306/mavenweb?useUnicode=true&characterEncoding=utf8"
    val driver = "com.mysql.jdbc.Driver"
    val user = "root"
    val password = "123456"
    val mysql_table = "t_user"

    val prop = new java.util.Properties()
    prop.setProperty("url",url)
    prop.setProperty("driver",driver)
    prop.setProperty("user",user)
    prop.setProperty("password",password)

    def getDF: DataFrame ={
        val time: Long = System.currentTimeMillis()
         val rdd: RDD[(String, String)] = sc.makeRDD(("hahah"+time,"hahah"+time)::("dddss2"+time,"dddss2"+time)::Nil)
         val df: DataFrame = rdd.toDF("user_name","password")
         return df
    }
    //TODO 1. 使用特定的向mysql写数据
    def df_write_jdbc(): Unit ={
        val df: DataFrame = getDF
        df.write.mode(SaveMode.Append).jdbc(url,mysql_table,prop)
    }
    ////TODO 2.使用统一的写入方式
    def df_write_format(): Unit ={
        val df: DataFrame = getDF
        df.write.format("jdbc")
                .mode(SaveMode.Append)
                .option("url",url)
                .option("driver",driver)
                .option("user",user)
                .option("password",password)
                .option("dbtable",mysql_table)
                .save()
    }
    def main(args: Array[String]): Unit = {
        df_write_format
        println("ok")
    }

}
