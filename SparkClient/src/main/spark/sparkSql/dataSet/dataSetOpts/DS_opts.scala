package sparkSql.dataSet.dataSetOpts

import org.apache.spark.sql.{Dataset, SparkSession,functions}
import sparkSql.dataSet.createDataSet.Seq_To_DS.{Person, spark}

object DS_opts {
    val spark:SparkSession = SparkSession.builder().appName("column").master("local[2]").getOrCreate()
    import spark.implicits._

    case class Person(name: String, age: Long)
    //创建一个DS对象
    val default_ds ={
        val persons = Seq(Person("zhangsan", 32),Person("lisi", 34))

        //TODO 方式2：使用sparkSession对象
        val ds_02: Dataset[Person] = spark.createDataset(persons)
        ds_02
    }

    //TODO 1. ds.take 返回array对象
    def take(): Unit ={
        default_ds.show()
        val personsArray: Array[Person] = default_ds.take(1)
        for (elem <- personsArray) {
            println(s"elem => $elem")
        }
    }

    //TODO 结合 functions进行过滤
    def filter(): Unit ={
        val filteredDS: Dataset[Person] = default_ds.filter(functions.length( functions.trim($"name")) > 5 )
        filteredDS.show()
    }

    def main(args: Array[String]): Unit = {
        filter()
    }

}
