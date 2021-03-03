package sparkSql.dataFrame.DF_UDF_UDAF

import org.apache.spark.sql.{DataFrame, functions}
import sparkSql.dataFrame.DF_DSL.DF_Data

object DF_UDF extends DF_Data {
    //TODO 必须导入隐士转换
    import spark.implicits._

    //TODO 注册自定义UDF函数
    def DF_register: Unit ={
        //自定义UDF函数
        def dealName(name: String): String = {
            name+"_mtf"
        }
        //注册自定义UDF函数
        spark.udf.register("dealName", dealName _);
    }

    //TODO DF_DSL风格的方式使用自定义函数
    def DF_UDF_DSL: Unit = {
        DF_register
        //TODO 使用 functions.expr 可以使用 DSL风格调用自定义注册的函数
        df.select($"name",functions.expr("dealName(name) as newName"), $"age").show(2)

    }
    //TODO DF_UDF_SQL风格的方式使用自定义UDF函数
    def DF_UDF_SQL: Unit ={
        DF_register
        df.createOrReplaceTempView("tmp_table_name")
        val sqlDF: DataFrame = spark.sql("select name, dealName(name) as newName,age from tmp_table_name")
        sqlDF.show()
    }

    def main(args: Array[String]): Unit = {
        DF_UDF_SQL
    }
}
