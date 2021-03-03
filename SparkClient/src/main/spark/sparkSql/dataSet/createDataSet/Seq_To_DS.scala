package sparkSql.dataSet.createDataSet

import org.apache.spark.sql.{Dataset, SparkSession}

object Seq_To_DS {
    val spark:SparkSession = SparkSession.builder().appName("column").master("local[2]").getOrCreate()
    import spark.implicits._


    case class Person(name: String, age: Long)

    /**TODO 方式1：使用 Seq 创建 DataSet
      * TODO 使用的是隐士转换：
      * implicit def localSeqToDatasetHolder[T : Encoder](s: Seq[T]): DatasetHolder[T] = {
      * DatasetHolder(_sqlContext.createDataset(s))
      * }
      * 继续跟踪，实质使用的是：
      *     sparkSession.createDataset(data)
      * 这种方式创建的DS.
      */
    def seq_to_DS: Unit ={
        //TODO 方式1：使用隐士转换
        val persons = Seq(Person("zhangsan", 32),Person("lisi", 34))
        // 必须先导入隐士转换，进行功能扩展：import spark.implicits._
        val ds_01: Dataset[Person] = persons.toDS()
        ds_01.show()

        //TODO 方式2：使用sparkSession对象
        val ds_02: Dataset[Person] = spark.createDataset(persons)
        ds_02.show()
    }

    def main(args: Array[String]): Unit = {
        seq_to_DS
    }

}
