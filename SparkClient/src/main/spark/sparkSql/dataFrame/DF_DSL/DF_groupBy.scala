package sparkSql.dataFrame.DF_DSL

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, RelationalGroupedDataset, SparkSession}

object DF_groupBy {
    val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()

    val sc: SparkContext = spark.sparkContext

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
    import spark.implicits._


    def df_groupBy: Unit ={
        //1. 根据姓名进行分组 (可以多列进行分组)
        val dataset_groupBy: RelationalGroupedDataset = df.groupBy($"name")

        //2. 求组内最大age值
        val maxAge_DF: DataFrame = dataset_groupBy.max( "age")
        maxAge_DF.show()

        //3.求各个组内的记录数
        val counts_DF: DataFrame = dataset_groupBy.count()
        counts_DF.show()
    }

    def main(args: Array[String]): Unit = {
        df_groupBy
    }
}
