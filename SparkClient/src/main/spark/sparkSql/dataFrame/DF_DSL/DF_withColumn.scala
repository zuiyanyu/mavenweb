package sparkSql.dataFrame.DF_DSL

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession, functions}



/**
  * DataFram列操作_withColumn()
  *
  * 一、说明
  * 1、常用列对象：' 、$ 、col 、column；
  *
  * 2、withColumn的第二个参数要传入已有列的Column对象，否则会报错；
  *
  * 3、sql.functions.lit()函数,返回的也是列对象，可以传入任意参数值；
  */
object DF_withColumn {
    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()

    private val sc: SparkContext = spark.sparkContext

    //1. 创建DF中的数据 （rdd的形式）
    val rdd: RDD[(String, Int)] = sc.parallelize(Array(("zhangsan",20),("zhangsan2",21)))
    //2. rdd直接进行转换成DF,会进行类型自动推断
    import spark.implicits._
    val df: DataFrame = rdd.toDF("name","age").cache()
   // df 的原始数据格式
    /**
      * |     name|age|
      * +---------+---+
      * | zhangsan| 20|
      * |zhangsan2| 21|
      */
    //df.show()


    //TODO 1. 为列进行重命名  等价于mysql的 alter
    /**
      * +---------+---+
      * |  newName|age|
      * +---------+---+
      * | zhangsan| 20|
      * |zhangsan2| 21|
      * +---------+---+
      */
    def df_withColumnRenamed(): Unit ={
        //仅仅 为已经存在的列名重新命名
        df.withColumnRenamed("name","newName")
                .show()

    }
    /** TODO 2. 添加列新列，或替换已有的列(有相同名字的列)，返回新的数据集。 等价mysql的 add 和 update
      * def withColumn(colName: String, col: Column)
      *
      */
    def df_withColumn(): Unit ={




        println("========1=============")
        /** TODO 1. 使用sql.functions.lit()函数，新增一列
          * def lit(literal : scala.Any) : org.apache.spark.sql.Column
          * sql.functions.lit()函数,返回的也是列对象，可以传入任意参数值；
          */
        //参数1：新列名  参数2：新列值
        val newColumn: org.apache.spark.sql.Column = functions.lit("22")
        val newDF1: DataFrame = df.withColumn("newColumn",newColumn)
        newDF1.show()
        /*
            +---------+---+---------+
            |     name|age|newColumn|
            +---------+---+---------+
            | zhangsan| 20|       22|
            |zhangsan2| 21|       22|
            +---------+---+---------+
         */

        println("=========2============")
        /**
          * TODO 2. 列对象：' 、$ 、functions.col 、functions.column，新增一列
          * TODO Column 表示了 Dataset 中的一个列, 并且可以持有一个表达式, 这个表达式作用于每一条数据, 对每条数据都生成一个值
          */
        val newDF2: DataFrame = df
                .withColumn("sno1", 'age)      // add: 新增一列“sno1”，列值和age列值一样
                .withColumn("age", $"age" + 1) //update: 新列值替换原始列值
        newDF2.show()
        /*
        +----+---------+---+
        |sno1|     name|age|
        +----+---------+---+
        |  20| zhangsan| 21|
        |  21|zhangsan2| 22|
        +----+---------+---+
         */

        df.withColumn("sno3",functions.col("age")+2).show()
        df.withColumn("sno4",functions.column("age")+3).show()

    }

    def main(args: Array[String]): Unit = {
        df_withColumn
//        var name = "hello"
//        val seq: immutable.IndexedSeq[Any] = name +: "2"
//        seq.foreach(println(_))

//        println(name)
    }


}
