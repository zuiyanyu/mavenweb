package sparkSql.load_write_data.write_data

import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import sparkSql.load_write_data.load_data.SparkRead_csv.{person_with_header, spark}

object df_write_csv {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()
    private val sc: SparkContext = spark.sparkContext
    import spark.implicits._
    case class TbDate(dateid:String, years:String,theyear:Integer,month:String,day:String,
                      weekday:String,week:String,quarter:String,period:String,halfmonth:String)
    /* 文件内容  有header
        id,name,age
        1, darren ,18
        2,\N,18
        3,"test",18
        4,'test2',18
     */
    private val person_with_header  = "D:\\hadoop\\sparkSql\\csvData\\person_with_header.csv"
    private val csvSavePath  = "D:\\hadoop\\sparkSql\\csvSavePath\\"


    //TODO 自动推断csv每一列数据类型 ：有header 头信息
    def sparkRead_csv: DataFrame = {
        //TODO 1.先读取csv文件，然后再写出去
        val df: DataFrame = spark.read.format("csv")
                .option("delimiter", ",")        // 分隔符，默认为逗号,
                .option("header", "true")        // true:首行作为标题  false:首行作为数据内容(默认为false)
                .option("quote", "'")            //引号字符，默认为双引号"
                .option("nullValue", "\\N")     //指定一个字符串代表 null 值
                .option("inferSchema", "true")  // true:自动推测字段类型 ,false:使用String类型  默认为false
                .load(person_with_header)
        df.show()
        df.printSchema()
        /*  可以看到 test2 不再带有 单引号了  .option("quote", "'") 起的作用
            +---+--------+---+
            | id|    name|age|
            +---+--------+---+
            |  1| darren | 18|
            |  2|    null| 18|
            |  3|  "test"| 18|
            |  4|   test2| 18|
            +---+--------+---+

            root
             |-- id: integer (nullable = true)
             |-- name: string (nullable = true)
             |-- age: integer (nullable = true)
        */
        return df
    }
    //TODO  问题：那么spark读写CSV到底有多少个属性可以设置呢？  class CSVOptions  源码，可以判断有多少个。
    def  save_csv(): Unit ={
        val read_csv: DataFrame = sparkRead_csv

        read_csv.write
                .format("csv")
                .option("header", "true")//保存文件的时候，保存头信息  false:不保存头信息
                .option("delimiter", "|")//指定分隔符
                //.option("quote", "")  //指定要被转义的字符。默认是转义双引号：比如 "test"会输出为:"\"test\"" (双引号被转义了)
                .option("nullValue","\\N") //写出的时候，将null值写成 \\N
                .option("ignoreLeadingWhiteSpace", false)
                .mode(SaveMode.Overwrite) //覆盖原始数据
                .save(csvSavePath)

        /**TODO 第一个变化：写出的文件会增加双引号\",会在有引号的地方再增加引号，因为双引号是默认值，如果不想增加，就把注释打开，设置引号为空即可
          *TODO 第二个变化：darren前后的空格没有了。在spark 2.1.1 使用 Spark SQL 保存 CSV 格式文件，默认情况下，会自动裁剪字符串前后空格。
          *  .option("quote", "\"")  输入数据格式如下： 没有文件头，并且 双引号被转义了
          * id|name|age
          * 1|darren|18
          * 2|\N|18
          * 3|"\"test\""|18
          * 4|test2|18
          *
          * TODO 这样的默认行为有时候并不是我们所期望的，在 Spark 2.2.0 之后，可以通过配置关闭改功能：
          * .option("ignoreLeadingWhiteSpace", false)  //是否裁剪字段值头部的空格
          * .option("ignoreTrailingWhiteSpace", false) //是否裁剪字段值尾部的空格
          * .option("nullValue", null) ////写出的时候，将null值写成空""  2|\N|18 变成了 2||18
          */
    }

    def main(args: Array[String]): Unit = {
        save_csv()
        println("ok")
    }

}
