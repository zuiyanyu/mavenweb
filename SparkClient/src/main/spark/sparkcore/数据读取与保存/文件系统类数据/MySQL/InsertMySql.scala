package sparkcore.数据读取与保存.文件系统类数据.MySQL

import org.apache.spark.{SparkConf, SparkContext}

//Mysql写入：
object InsertMySql {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[2]").setAppName("HBaseApp")
        val sc = new SparkContext(sparkConf)


        val datas = sc.parallelize(
            List(
                (13, 22, "0:0:0:0:0:0:0:1", "2020-11-23 11:11:29.0"),
                (14, 21, "0:0:0:0:0:0:0:1", "2020-11-23 11:11:29.0"),
                (15, 23, "0:0:0:0:0:0:0:1", "2020-11-23 11:11:29.0")
            )
        )


        //定义插入逻辑
        def insertData(iterator: Iterator[(Int,Int,String,String)]): Unit = {
            //3.定义连接mysql的参数
            val driver = "com.mysql.jdbc.Driver"
            val url = "jdbc:mysql://localhost:3306/mavenweb?useUnicode=true&characterEncoding=utf8"
            val userName = "root"
            val passWd = "123456"
            val insertSql = "insert into t_login_log(login_log_id,user_id,ip,login_datetime) values(?,?,?,?)"

            //一个分区一次连接，区内数据逐条插入
            Class.forName(driver).newInstance()
            val conn = java.sql.DriverManager.getConnection(url, userName, passWd)

            //遍历区内数据
            iterator.foreach(data => {
                //区内数据一条一条的插入
                val ps = conn.prepareStatement(insertSql)
                ps.setInt(1, data._1)
                ps.setInt(2, data._2)
                ps.setString(3, data._3)
                ps.setString(4, data._4)
                ps.executeUpdate()
            })
        }

        datas.foreachPartition(insertData)

    }
}
