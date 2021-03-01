package 数据读取与保存.文件系统类数据.MySQL

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 支持通过Java JDBC访问关系型数据库。需要通过JdbcRDD进行
  * （1）添加依赖
  * <dependency>
  * <groupId>mysql</groupId>
  * <artifactId>mysql-connector-java</artifactId>
  * <version>5.1.27</version>
  * </dependency>
  *
  */
object QueryMySql {
    def main(args: Array[String]): Unit = {
        //1.创建spark配置信息
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("JdbcRDD")

        //2.创建SparkContext
        val sc = new SparkContext(sparkConf)

        //3.定义连接mysql的参数
        val driver = "com.mysql.jdbc.Driver"
        val url = "jdbc:mysql://localhost:3306/mavenweb?useUnicode=true&characterEncoding=utf8"
        val userName = "root"
        val passWd = "123456"

        //创建JdbcRDD
        val rdd = new JdbcRDD(
            sc,
            () => {
                Class.forName(driver)
                DriverManager.getConnection(url, userName, passWd)
            },
            "select * from t_login_log where  ?<= login_log_id  and login_log_id <= ?",
            1,
            10,
            1,
            r =>(
                    r.getObject(1)
                    , r.getObject(2)
                    , r.getObject(3)
                    , r.getObject(4)
            )
        )

        //打印最后结果
        println(rdd.count())
        rdd.foreach(println)

        //关闭资源
        sc.stop()

    }
}
