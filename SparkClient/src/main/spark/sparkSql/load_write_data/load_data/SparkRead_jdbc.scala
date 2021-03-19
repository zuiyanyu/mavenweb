package sparkSql.load_write_data.load_data

import org.apache.spark.sql.{Row, SparkSession}

object SparkRead_jdbc {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .config("spark.sql.crossJoin.enabled", "true")
            .getOrCreate()


    import spark.implicits._


    val url = "jdbc:mysql://localhost:3306/mavenweb?useUnicode=true&characterEncoding=utf8"
    val driver = "com.mysql.jdbc.Driver"
    val user = "root"
    val password = "123456"
    val mysql_table = "t_user"

    //TODO 1. 使用特定的加载方式
    def sparkRead_jdbc(): Unit = {

        val prop = new java.util.Properties()
        prop.setProperty("url", url)
        prop.setProperty("driver", driver)
        prop.setProperty("user", user)
        prop.setProperty("password", password)

        val jdbcDF = spark.read.jdbc(url, mysql_table, prop)
        jdbcDF.show()

    }

    //TODO 2.使用统一的加载方式
    def sparkRead_format_option: Unit = {
        val jdbcDF = spark.read
                .format("jdbc")
                .option("url", url)
                .option("dbtable", mysql_table)
                .option("user", user)
                .option("password", password)
                .load()

        jdbcDF.show()
    }

    def sparkRead_format_options: Unit = {
        val jdbcDF = spark.read
                .format("jdbc")
                .options(Map(
                    "url" -> url,
                    "dbtable" -> mysql_table,
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

    //TODO 2.Spark SQL-使用SQL语句而不是表名使用JDBC加载数据-方式1
    def sparkRead_format_option_sql_type1: Unit = {
        /**
          * option("dbtable",sql) 的入参应该为一个表名 ，
          * 但是可以您应该传递一个有效的子查询作为dbtable参数，让然有效
          */
        val sql = "(select user_id,user_name from mavenweb.t_user) as t_user   "
        val jdbcDF = spark.read
                .format("jdbc")
                .option("url", url)
                .option("user", user)
                .option("password", password)
                .option("dbtable", sql)
                .load()

        jdbcDF.show()

        //TODO 遍历查询的结果集
        val rows: Array[Row] = jdbcDF.collect()
        for (row <- rows) {
            //println("user_id = "+ row.get(0) + "  ;user_name = " + row.get(1))
            println("user_id = " + row.getAs("user_id") + "  ;" +
                    "user_name = " + row.getAs("user_name"))
        }
    }

    //TODO 2.Spark SQL-使用SQL语句而不是表名使用JDBC加载数据-方式2
    def sparkRead_format_option_sql_type2: Unit = {
        /**
          * option("dbtable",sql) 的入参应该为一个表名 ，
          * 但是可以您应该传递一个有效的子查询作为dbtable参数，让然有效
          */
        val sql = "select user_id,user_name from mavenweb.t_user   "
        val jdbcDF = spark.read
                .format("jdbc")
                .option("url", url)
                .option("user", user)
                .option("password", password)
                .option("dbtable", mysql_table)
                .option("sql", sql)
                .load()

        jdbcDF.show()

        //TODO 遍历查询的结果集
        val rows: Array[Row] = jdbcDF.collect()
        for (row <- rows) {
            //println("user_id = "+ row.get(0) + "  ;user_name = " + row.get(1))
            println("user_id = " + row.getAs("user_id") + "  ;" +
                    "user_name = " + row.getAs("user_name"))
        }
    }

    //TODO 2.Spark SQL-使用SQL语句而不是表名使用JDBC加载数据-方式2
    //spark.read.format("jdbc").option("mode","insert") 可以插入数据到mysql
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
                .option("mode","insert")
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
        //sparkRead_jdbc
        sparkRead_format_option_sql_type2
    }
}
