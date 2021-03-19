package sparkSql.dataFrame.DF_SQL

import org.apache.spark.sql.SparkSession

object DF_SQL extends DF_Data {
    def main(args: Array[String]): Unit = {
        //DF_createOrReplaceTempView
        tmp
    } //TODO 1. 对DataFrame创建一个临时表  普通临时表是Session范围内的
    def tmp: Unit = {
        df.createOrReplaceTempView("tmp_people")
        println("======可以使用 * 查询所有信息======")
        val sqlDF = spark.sql("SELECT name as `张三 哈哈哈` FROM tmp_people")
        sqlDF.show()
    }
    def DF_select_first: Unit ={
        df.createOrReplaceTempView("tmp_people")
        val sqlDF = spark.sql("SELECT first(0) FROM tmp_people where 1=0")
        sqlDF.show() //first(0, false)   null

        val sqlDF2 = spark.sql("SELECT first(1) FROM tmp_people  ")
        sqlDF2.show() //first(0, false)   0

        val sqlDF3 = spark.sql("SELECT first(1) FROM tmp_people  ")
        sqlDF3.show() //first(1, false)   1
    }
    //TODO 1. 对DataFrame创建一个临时表  普通临时表是Session范围内的
    def DF_createOrReplaceTempView: Unit ={
        df.createOrReplaceTempView("tmp_people")

        println("======可以使用 * 查询所有信息======")
        val sqlDF = spark.sql("SELECT * FROM tmp_people")
        sqlDF.show()

        println("======可以给字段取别名======")
        spark.sql("SELECT age as age2 FROM tmp_people").show

        println("======可以使用聚合函数======")
        spark.sql("SELECT count(1) as count, max(age) as maxAge FROM tmp_people group by name").show

        println("======普通临时表是Session范围内的,其他session访问不到======")
        val newSpark: SparkSession = spark.newSession()
        //异常：org.apache.spark.sql.AnalysisException: Table or view not found: tmp_people
        newSpark.sql("SELECT * FROM tmp_people").show()

    }

    //TODO 2. 对于DataFrame创建一个全局表  在应用范围内有效
    //注意：普通临时表是Session范围内的，
    // 如果想应用范围内有效，可以使用全局临时表。使用全局临时表时需要全路径访问，如：global_temp.people
    def createGlobalTempView: Unit ={
        df.createGlobalTempView("global_people")

        //TODO 1. 使用全局临时表时需要全路径访问，如：global_temp.people
        /*
        //Exception in thread "main" org.apache.spark.sql.AnalysisException: Table or view not found: global_people
        spark.sql("SELECT * FROM global_people").show()
        */

        //TODO 2. 使用当前 sparkSession访问全局表
        val sqlDF = spark.sql("SELECT * FROM global_temp.global_people")
        sqlDF.show()

        //TODO 3. 使用其他的sparkSession访问全局表
        println("======全局临时表在应用范围内有效,其他session可访问到======")
        val newSpark: SparkSession = spark.newSession()
        newSpark.sql("SELECT * FROM global_temp.global_people").show()

    }


}
