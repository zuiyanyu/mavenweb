package sparkSql.load_write_data.load_data

//import javax.servlet.FilterRegistration
//import javax.servlet.http.HttpServlet
import org.apache.spark.SparkContext
import org.apache.spark.sql.catalyst.ScalaReflection
import org.apache.spark.sql.types.{StructField, StructType}
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SparkRead_csv {
    //FilterRegistration
//    HttpServlet
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

    /* 文件内容  没有header
        1, darren ,18
        2,\N,18
        3,"test",18
        4,'test2',18
    */
    private val person_without_header  = "D:\\hadoop\\sparkSql\\csvData\\person_without_header.csv"

    //TODO 自动推断csv每一列数据类型 ：有header 头信息
    def sparkRead_csv_a_01: Unit = {
        val df: DataFrame = spark.read.format("mycsv") // format("csv")
                .option("delimiter", ",")        // 分隔符，默认为逗号,
                .option("encoding", "UTF-8")     // 使用UTF-8进行数据读取
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

    }
    //TODO 自动推断csv每一列数据类型 ：没有header 头信息， 手动添加头信息
    def sparkRead_csv_a_02: Unit = {
        //val tbDate_NO_Header  = "D:\\hadoop\\sparkSql\\csvData\\tbDate_NO_Header.csv"
        val df: DataFrame = spark.read.format("csv")
                .option("delimiter", ",") // 分隔符，默认为逗号,
                //TODO 没有头信息
                .option("header", "false") // true:首行作为标题  false:首行作为数据内容(默认为false)
                .option("quote", "'") //引号字符，默认为双引号"
                .option("nullValue", "\\N") //指定一个字符串代表 null 值
                //TODO 自动类型推断
                .option("inferSchema", "true") // true:自动推测字段类型,false:使用String类型 (默认为false)
                .load(person_without_header)
                // TODO 没有header 头信息， 手动添加头信息
                .toDF("id"," name","age")
                //.toDF(Array("id"," name","age").mkString(","))
        df.show()
        df.printSchema()

        /**
          * +---+--------+---+
          * | id|    name|age|
          * +---+--------+---+
          * |  1| darren | 18|
          * |  2|    anne| 18|
          * |  3|  "test"| 18|
          * |  4|   test2| 18|
          * +---+--------+---+
          *
          * root
          * |-- id: integer (nullable = true)
          * |--  name: string (nullable = true)
          * |-- age: integer (nullable = true)
          */

    }

    //TODO 自动推测字段类型只是折中方案，更好的方案是指定字段类型：
    //TODO 指定字段类型: 如果csv文件不带头信息。 使用样例类同时指定头信息和字段类型
   case class Person(id:String,name:String,age:Integer)
    def sparkRead_csv_b_01: Unit = {
        val df: DataFrame = spark.read.format("csv")
                .option("delimiter", ",") // 分隔符，默认为逗号,
                //TODO 无头信息
                .option("header", "false") // true:首行作为标题  false:首行作为数据内容(默认为false)
                .option("quote", "'") //引号字符，默认为双引号"
                .option("nullValue", "\\N") //指定一个字符串代表 null 值
                //TODO 不进行类型推断
                .option("inferSchema", "false") // true:自动推测字段类型,false:使用String类型 (默认为false)
                //TODO 这里利用样例类同时指定头信息和字段类型  需要一个 schema: StructType 入参
                .schema(ScalaReflection.schemaFor[Person].dataType.asInstanceOf[StructType])
                .load(person_without_header)
        df.show()
        df.printSchema()

        /**
          * +---+--------+---+
          * | id|    name|age|
          * +---+--------+---+
          * |  1| darren | 18|
          * |  2|    anne| 18|
          * |  3|  "test"| 18|
          * |  4|   test2| 18|
          * +---+--------+---+
          *
          * root
          * |-- id: string (nullable = true)
          * |-- name: string (nullable = true)
          * |-- age: integer (nullable = true)
          */
    }
    //TODO 指定字段类型: 如果csv文件带头信息
    def sparkRead_csv_b_02: Unit = {
        val tbDate_NO_Header  = "D:\\hadoop\\sparkSql\\csvData\\tbDate_NO_Header.csv"

        val df: DataFrame = spark.read.format("csv")
                .option("delimiter", ",") // 分隔符，默认为逗号,
                .option("header", "true") // true:首行作为标题  false:首行作为数据内容(默认为false)
                .option("quote", "'") //引号字符，默认为双引号"
                .option("nullValue", "\\N") //指定一个字符串代表 null 值
                .option("inferSchema", "true") // true:自动推测字段类型,false:使用String类型 (默认为false)
                //TODO 指定字段与类型: 如果csv文件不带头信息  需要一个 schema: StructType 入参
                .schema(ScalaReflection.schemaFor[TbDate].dataType.asInstanceOf[StructType])
                .load(tbDate_NO_Header)
        df.show()
        df.printSchema()
    }

    def main(args: Array[String]): Unit = {
        sparkRead_csv_a_01
    }
}
