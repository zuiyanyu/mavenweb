package sparkSql.dataFrame.DF_DSL

import org.apache.spark.sql.{DataFrame, SparkSession}

object SQL风格语法 {
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

        jsonDataFrame.createOrReplaceTempView("peoson")
        jsonDataFrame.show();
    }

}
