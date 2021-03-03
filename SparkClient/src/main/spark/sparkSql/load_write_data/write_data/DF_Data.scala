package sparkSql.load_write_data.write_data

import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SparkSession}

class DF_Data {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()
    private val sc: SparkContext = spark.sparkContext

    import spark.implicits._

    def getDF: DataFrame = {
        val df: DataFrame = sc.makeRDD(
                    ("1", "zhangsan", "20") ::
                    ("2", "zhangsan2", "22") ::
                    ("3", "wangwu", "23") ::
                    ("4", "wangwu2", "24") ::
                    Nil
        ).toDF("id", "name", "age")
        df
    }
}
