package sparkSql.load_write_data.load_data

import org.apache.spark.sql.SparkSession

object SparkRead_jdbc {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()
    import spark.implicits._


    val url = "jdbc:mysql://localhost:3306/mavenweb?useUnicode=true&characterEncoding=utf8"
    val driver = "com.mysql.jdbc.Driver"
    val user = "root"
    val password = "123456"
    val mysql_table = "t_user"

    //TODO 1. 使用特定的加载方式
    def sparkRead_jdbc(): Unit ={

        val prop = new java.util.Properties()
        prop.setProperty("url",url)
        prop.setProperty("driver",driver)
        prop.setProperty("user",user)
        prop.setProperty("password",password)

        val jdbcDF = spark.read.jdbc(url,mysql_table,prop)
        jdbcDF.show()

    }
    //TODO 2.使用统一的加载方式
    def sparkRead_format_option: Unit ={
        val jdbcDF = spark.read
                .format("jdbc")
                .option("url", url)
                .option("dbtable",mysql_table)
                .option("user", user)
                .option("password", password)
                .load()

        jdbcDF.show()
    }
    def sparkRead_format_options: Unit ={
        val jdbcDF = spark.read
                .format("jdbc")
                .options(Map(
                    "url" -> url,
                    "dbtable" ->mysql_table,
                    "driver" -> driver,
                    "user" -> user,
                    "password" -> password,
                    "fetchSize" -> "10",
                    "partitionColumn" -> "password",
                    "lowerBound" -> "0",
                    "upperBound" -> "1000",
                    "numPartitions" -> "2"
                ))
                .load()

        jdbcDF.show()
    }
    def main(args: Array[String]): Unit = {
        //sparkRead_jdbc
        sparkRead_format_options
    }
}
