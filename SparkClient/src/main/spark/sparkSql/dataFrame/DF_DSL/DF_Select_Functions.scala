package sparkSql.dataFrame.DF_DSL

import org.apache.spark._
import org.apache.spark.sql.{DataFrame, functions}
import sparkSql.dataFrame.DF_DSL.DF_Select.df
import java.util.Calendar
import java.text.SimpleDateFormat

import sparkSql.load_write_data.write_data.df_write_txt.{getDF, sc, sqlContext}
import sparkSql.load_write_data.write_data.format.CustomTextOutputFormat

// functions 里面封装的都是 一些类似mysql中使用的函数
object DF_Select_Functions extends DF_Data {

    import spark.implicits._

    def main(args: Array[String]): Unit = {
        function_regexp_replace
        //functions_expr
    }

    //TODO concat 拼接字符串，并且使用正则表达式替换某个值中的特殊字符
    def function_regexp_replace(): Unit = {
        /**
          * +---------+---+
          * |     name|age|
          * +---------+---+
          * | zhangsan| 20|
          * |zhangsan2| 22|
          * +---------+---+
          */
        df.show()
        val df2: DataFrame = df.withColumn("name2",  functions.expr("concat('+',name,'.')") )
        val newDF: DataFrame = df2.withColumn(
            "newName",
            functions.regexp_replace(functions.trim($"name2"),"(^\\+)|(\\.$)","")
        )
        newDF.show();
        //df.select(functions.monotonically_increasing_id(), $"age").show(2)
    }

    def function_trim(): Unit = {
        val newDF: DataFrame = df.withColumn("newName",functions.trim($"name"))
        newDF.show();
        //df.select(functions.monotonically_increasing_id(), $"age").show(2)
    }

    //1. 生成递增序列号
    def monotonically_increasing_id(): Unit = {
        df.select(functions.monotonically_increasing_id(), $"age").show(2)


    }

    def functions_expr: Unit = {
        //TODO
        val newDF = df.select(functions.expr("name"), //查看列
            functions.expr("name as newName"), //给列取别名
            functions.expr("abs(age) as abs_age")) //计算列：给列使用内置函数
        newDF.show()


        //TODO 使用 functions.expr 可以使用 DSL风格调用自定义注册的函数
        def dealName(name: String): String = {
            "aaa"
        }
        spark.udf.register("dealName", dealName _);
        // spark.udf.register("monotonically_increasing_id()",functions.monotonically_increasing_id _);
        //风格1：
        df.select(functions.expr("dealName(name) as monotonically_increasing_id"), $"age").show(2)
        //风格2：
        df.select(functions.expr("dealName(name)") as ("monotonically_increasing_id"), $"age").show(2)

    }
    def functions_groupBy: Unit ={
        val sqlContext =  spark.sqlContext
        //配置输出文件不生成success文件
        sc.hadoopConfiguration.set("mapreduce.fileoutputcommitter.marksuccessfuljobs", "false")
        //配置一些参数
        //如果设置为true，sparkSql将会根据数据统计信息，自动为每一列选择单独的压缩编码方式
        sqlContext.setConf("spark.sql.inMemoryColumnarStorage.compressed", "true")
        //控制列式缓存批量的大小。增大批量大小可以提高内存的利用率和压缩率，但同时也会带来OOM的风险
        sqlContext.setConf("spark.sql.inMemoryColumnarStorage.batchSize", "1000")
        sqlContext.setConf("spark.sql.autoBroadcastJoinThreshold", "10485760")
        //设为true，则启用优化的Tungsten物理执行后端。Tungsten会显示的管理内存，并动态生成表达式求值得字节码
        sqlContext.setConf("spark.sql.tungsten.enabled", "true")
        //配置shuffle时使用的分区数
        sqlContext.setConf("spark.sql.shuffle.partitions", "200")

        sc.setLogLevel("WARN")
        val df: DataFrame = getDF
        df.show()
        /**
          * +---+---------+---+
          * | id|     name|age|
          * +---+---------+---+
          * |  1| zhangsan| 20|
          * |  2|zhangsan2| 22|
          * |  3|   wangwu| 23|
          * |  4|  wangwu2| 24|
          * +---+---------+---+
          */
         //先根据名字前5个字符进行分组聚合， 再 组内聚合求出聚合值 ，并给出别名
        val resDF: DataFrame = df.groupBy(functions.substring(functions.col("name"), 0, 5).as("name"))
                .agg(
                    functions.max("age") as ("max"),
                    functions.avg("age") as ("avg")
                )
        resDF.show()
        sc.stop()
        /**
          * +-----+---+----+
          * | name|max| avg|
          * +-----+---+----+
          * |wangw| 24|23.5|
          * |zhang| 22|21.0|
          * +-----+---+----+
          */
    }



}
