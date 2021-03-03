package sparkSql.dataFrame.DF_UDF_UDAF

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SparkSession, functions}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._
import sparkSql.dataFrame.DF_DSL.DF_Select_Functions.sc

/*
TODO 聚合函数
强类型的Dataset和弱类型的DataFrame都提供了相关的聚合函数， 如 count()，countDistinct()，avg()，max()，min()。
除此之外，用户可以设定自己的自定义聚合函数。

TODO 弱类型用户自定义聚合函数：通过继承 UserDefinedAggregateFunction来实现用户自定义聚合函数。
 */
//TODO UDAF 多进一出
object DF_UDAF {
    val spark = SparkSession.builder().appName("DF_UDAF").master("local[2]").getOrCreate()
    private val sc: SparkContext = spark.sparkContext
    private val conf: SparkConf = sc.getConf
    //获取 application id
    private val appid: String = conf.getAppId
    //导入隐士转换
    import spark.implicits._

    def main(args: Array[String]): Unit = {
        println("appid = " + appid)
        DSL_UDAF
    }

    //TODO  获取RDD
    def getRDD = {
        sc.makeRDD(
            Array(
                ("zhangsan", 3000),
                ("zhangsan", 7000),
                ("zhangsan2", 4500),
                ("lisi", 3500),
                ("wangwu", 2000)
            )
        )
    }

    //TODO  获取DF
    case class Person(name: String, salary: Double)

    def getDF: DataFrame = {
        //获取rdd数据，并将元素转换为样例类
        val rdd: RDD[(String, Int)] = getRDD
        val personRDD: RDD[Person] = rdd.map { case (name, salary) => Person(name, salary) }

        val df: DataFrame = personRDD.toDF()
        df
    }

    def SQL_UDAF = {
        val df: DataFrame = getDF
        //将DF 注册为 临时表
        df.createOrReplaceTempView("tmp_tab")

        //使用sql的形式
        spark.sql("select * from tmp_tab").show;

        spark.udf.register("myAverage", MyAverage)
        spark.sql("select name, myAverage(salary) from tmp_tab group by name ").show;
        /*
            +---------+------------------+
            |     name|myaverage$(salary)|
            +---------+------------------+
            |   wangwu|            2000.0|
            |zhangsan2|            4500.0|
            | zhangsan|            5000.0|
            |     lisi|            3500.0|
            +---------+------------------+
         */
    }

    //TODO DF_UDF_SQL风格的方式使用自定义UDAF函数
    def DSL_UDAF = {
        //1. 获取df，以及注册自定义聚合函数
        val df: DataFrame = getDF
        spark.udf.register("myAverage", MyAverage)

        //2. 简单使用自定义聚合函数
        val resDF = df.agg(functions.expr("myAverage(salary)") as ("myAverage"))
        resDF.show()
        /**
          * +---------+
          * |myAverage|
          * +---------+
          * |   4000.0|
          * +---------+
          */

        //3. 分组使用自定义聚合函数
        val resDF2: DataFrame = df.groupBy(functions.substring(functions.col("name"), 0, 5).as("name"))
                .agg(
                    functions.max("salary") as ("max"),
                    functions.avg("salary") as ("avg"),
                    functions.expr("myAverage(salary)") as ("myAverage")
                )
        resDF2.show()

        /**
          * +-----+------+-----------------+-----------------+
          * | name|   max|              avg|        myAverage|
          * +-----+------+-----------------+-----------------+
          * |wangw|2000.0|           2000.0|           2000.0|
          * | lisi|3500.0|           3500.0|           3500.0|
          * |zhang|7000.0|4833.333333333333|4833.333333333333|
          * +-----+------+-----------------+-----------------+
          */
        sc.stop()
    }
}

// TODO 弱类型用户自定义聚合函数：通过继承 UserDefinedAggregateFunction来实现用户自定义聚合函数。
// select name,myAverage(salary) from tmp_tab group by name
// 输入值：salary  输出值：myAverage(salary)  缓存值：因为是多进一出，必定有一个缓冲区缓冲多个中间处理值
object MyAverage extends UserDefinedAggregateFunction {

    //输入类型： 聚合函数输入参数的数据类型（参数结构）  自定义一个输入值的名子，输入值类型，输入值是否能为空(null)
    override def inputSchema: StructType = {
        //StructType(Array(StructField("inputColumn",DoubleType,false)))
        StructType(StructField("inputColumn", DoubleType, false) :: Nil)
    }

    //缓存区的数据值类型：聚合缓冲区中值得的数据类型
    override def bufferSchema: StructType = {
        // StructType(StructField("sum", LongType) :: StructField("count", LongType) :: Nil)
        new StructType()
                .add("sum", DoubleType)
                .add("count", LongType)
    }

    // 输出类型：返回值的数据类型
    override def dataType: DataType = DoubleType

    // 对于相同的输入是否一直返回相同的输出。   /dɪˌtɜːmɪˈnɪstɪk/ 确定性的
    override def deterministic: Boolean = true

    // 缓存区的数据值：初始化
    // bufferSchema: StructType 定义缓存区数据值的类型
    override def initialize(buffer: MutableAggregationBuffer): Unit = {
        //   buffer(0) = 0D
        buffer.update(0, 0D) // sum = 0
        buffer.update(1, 0L) // count = 0
    }

    //分区内聚合：
    override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
        //1. 获取缓冲区中的中间值
        // val sum =buffer(0) //  def apply(i: Int): Any = get(i)
        val oldSum = buffer.getDouble(0)
        val oldCount: Long = buffer.getLong(1)

        //获取输入值
        val inputValue: Double = input.get(0).toString.toDouble

        //println(s"update: oldSum = $oldSum ,oldCount = $oldCount , inputValue = $inputValue")

        //累加输入值 与输入值数量
        buffer.update(0, oldSum + inputValue) // sum = 0
        buffer.update(1, oldCount + 1) // count = 0
        //println(s"update: sum = ${buffer.get(0)} ,count = ${buffer.get(1)}  ")
    }

    //分区间聚合：讲
    //abstract class MutableAggregationBuffer extends Row
    override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
        //当前的分区 得到的中间值  当前executor
        val sum1: Double = buffer1.getDouble(0)
        val count1: Long = buffer1.getLong(1)

        //其他分区 得到的中间值
        val sum2: Double = buffer2.getDouble(0)
        val count2: Long = buffer2.getLong(1)


        //println(s"merge: sum1 = $sum1 ,count1 = $count1 , sum2 = $sum2  , count2 = $count2" )

        //3. 将其他分区的中间值聚合到当前分区的中间值中
        buffer1.update(0, sum1 + sum2)
        buffer1.update(1, count1 + count2)


    }

    // 计算最终结果:平均值
    override def evaluate(buffer: Row): Any = {
        val sum: Double = buffer.getDouble(0)
        val count: Long = buffer.getLong(1)
        //println(s"sum = $sum , count = $count")
        if (count == 0) {
            return 0.00;
        } else {
            sum / count
        }
    }
}

