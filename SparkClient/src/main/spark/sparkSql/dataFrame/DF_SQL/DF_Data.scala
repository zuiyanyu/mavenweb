package sparkSql.dataFrame.DF_SQL

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import sparkSql.dataFrame.DF_DSL.DF_groupBy.sc

class DF_Data {
    val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()

    val sc: SparkContext = spark.sparkContext

    //1. 创建DF中的数据 （rdd的形式）
    //1. 创建DF中的数据 （rdd的形式）
    val rdd: RDD[(String, Int)] = sc.parallelize(
        Array(
            ("zhangsan",20),
            ("lisi",23),
            ("lisi",24),
            ("zhangsan",26),
            ("zhangsan",27)
        )
    )
    //2. rdd直接进行转换成DF,会进行类型自动推断
    import spark.implicits._
    val df: DataFrame = rdd.toDF("name","age").cache()
    /** df 的原始数据格式
      * |     name|age|
      * +---------+---+
      * | zhangsan| 20|
      * |zhangsan2| 21|
      */

}
