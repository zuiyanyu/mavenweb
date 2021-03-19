package sparkSql.load_write_data.write_data

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import sparkSql.load_write_data.load_data.SparkRead_jdbc.{mysql_table, password, spark, url, user, _}

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
    //TODO 2.Spark SQL-使用SQL语句而不是表名使用JDBC加载数据-方式2
    def sparkRead_format_option_sql_type2_insert_update: Unit = {
        /**
          * option("dbtable",sql) 的入参应该为一个表名 ，
          * 但是可以您应该传递一个有效的子查询作为dbtable参数，让然有效
          */
        val insertSql = "insert into  mavenweb.t_user(user_id,user_name) values" +
                "(2133,'insertSql'),(21333,'insertSql2')  "
        //val updateSql = "select user_id,user_name from mavenweb.t_user   "

        val insertJdbcDF = spark.read
                .format("jdbc")
                .option("url", url)
                .option("user", user)
                .option("password", password)
                .option("dbtable", mysql_table)

                .option("sql", insertSql)
                .load()
        insertJdbcDF.show();
        val insertCount: Long = insertJdbcDF.count()
        println(s"insertCount = $insertCount")

        println("=================================")
        //        val jdbcDF2 = spark.read
        //                .format("jdbc")
        //                .option("url", url)
        //                .option("user", user)
        //                .option("password", password)
        //                .option("dbtable", mysql_table)
        //                .option("sql", insertSql)
        //                .load()


    }
    def main(args: Array[String]): Unit = {
        df_write_format
        println("ok")
    }

}
