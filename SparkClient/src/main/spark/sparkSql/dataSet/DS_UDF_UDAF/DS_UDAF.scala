package sparkSql.dataSet.DS_UDF_UDAF

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql._
import org.apache.spark.sql.expressions.Aggregator
import sparkSql.dataSet.DS_UDF_UDAF


/**
  *  TODO 强类型用户自定义聚合函数：通过继承Aggregator来实现强类型自定义聚合函数，同样是求平均工资
  */
object DS_UDAF {
    private val spark: SparkSession = SparkSession.builder().appName("RDD_To_DS").master("local[2]").getOrCreate()
    private val sc: SparkContext = spark.sparkContext
    import spark.implicits._

    //TODO  获取RDD
    def getRDD  = {
        sc.makeRDD(
            Array(
                ("zhangsan",3000),
                ("zhangsan",7000),
                ("zhangsan2",4500),
                ("lisi",3500),
                ("wangwu",2000)
            )
        )
    }

    def getDS: Dataset[Person] ={
        //将rdd转换为DF
        val rdd: RDD[(String, Int)] = getRDD
        val df: DataFrame = rdd.toDF("name","salary")

        //TODO dataFrame 转换为 DataSet
        //def as[U : Encoder]: Dataset[U] = Dataset[U](sparkSession, logicalPlan)
        val ds: Dataset[Person] = df.as[Person]

        return ds ;
    }

    //强类型的自定义聚合函数只能使用DSL风格进行使用
    def DS_UDAF_DSL: Unit ={
        val ds: Dataset[Person] = getDS
        val average: MyAverage.type = MyAverage
        //TODO 将聚合函数转换为查询列
        val myAvrage: TypedColumn[DS_UDF_UDAF.Person, Double] = average.toColumn.name("myAverage")

        //TODO 查询指定列
        ds.select(myAvrage).show()
        /*
            +---------+
            |myAverage|
            +---------+
            |   4000.0|
            +---------+
         */

       /*
        // org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema cannot be cast to sparkSql.dataSet.DS_UDF_UDAF.Person
        val groupby: RelationalGroupedDataset = ds.groupBy("name")
        groupby.agg(myAvrage).show()
        */
    }

    def main(args: Array[String]): Unit = {
        DS_UDAF_DSL
    }


}
case class Person(name: String, salary: Double)
case class Average(var sum:Double,var count:Long)
//Tuple2[Double,Long] : (sum，count)
object MyAverage extends Aggregator[Person,Average,Double]{
    override def zero: Average = Average(0,0)

    //区内聚合 ：
    override def reduce(b: Average, p: Person): Average = {
        //新传入的入参值
        val inputValue: Double = p.salary

        //分区内聚合
        b.sum = b.sum + inputValue ;
        b.count = b.count + 1 ;
        return  b ;
    }
    //分区间聚合
    override def merge(b1: Average, b2: Average): Average = {
        b1.sum = b1.sum + b2.sum
        b1.count = b1.count + b2.count ;

        return b1;
    }

    override def finish(reduction: Average): Double = {
        val count: Double = reduction.count
        val sum: Double = reduction.sum

        if(count == 0){
            return 0.00 ;
        }else{
            sum / count;
        }
    }

    // 设定区间值类型的编码器，要转换成case类
    // Encoders.product是进行scala元组和case类转换的编码器
    override def bufferEncoder: Encoder[Average] = {
        Encoders.product
    }

    // 设定最终输出值的编码器
    override def outputEncoder: Encoder[Double] = {
        Encoders.scalaDouble
    }
}
