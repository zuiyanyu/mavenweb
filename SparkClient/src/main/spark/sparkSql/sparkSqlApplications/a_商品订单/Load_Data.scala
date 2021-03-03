package sparkSql.sparkSqlApplications.a_商品订单

import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession}
import sparkSql.load_write_data.write_data.df_write_csv.person_with_header

/*
表数据csv格式  逗号分割

tbDate.csv
dateid,years,theyear,month,day,weekday,week,quarter,period,halfmonth
2018/3/13,Mar-18,2018,3,13,3,3,3,3,3
2018/2/13,Feb-18,2018,2,13,3,3,3,3,3
2019/1/13,Jan-19,2019,1,13,3,3,3,3,3
2019/3/13,Mar-19,2019,3,13,3,3,3,3,3
2018/9/13,Sep-18,2018,9,13,3,3,3,3,3
2018/11/13,Nov-18,2018,11,13,3,3,3,3,3
2017/12/13,Dec-17,2017,12,13,3,3,3,3,3
2017/5/13,May-17,2017,5,13,3,3,3,3,3

tbStockDetail.csv
ordernumber,rownum,itemid,number,price,amount
lj111,12,item11,10,100,300
lj112,12,item12,10,100,200
lj113,12,item13,10,100,300
lj114,12,item14,10,100,100
lj115,12,item15,10,100,300
lj116,12,item16,10,100,700
lj117,12,item17,10,100,600
lj118,12,item18,10,100,500

tbStock.csv
ordernumber,locationid,dateid
lj111,jd,2018/3/13
lj112,jd,2018/2/13
lj113,jd,2019/1/13
lj114,jd,2019/3/13
lj115,jd,2018/9/13
lj116,jd,2018/11/13
lj117,jd,2017/12/13
lj118,jd,2017/5/13
  */
//TODO 加载数据：
class Load_Data {
    val spark = SparkSession.builder().appName("Load_Data").master("local[*]").getOrCreate()
    private val sqlContext: SQLContext = spark.sqlContext
    private val sc: SparkContext = spark.sparkContext

    import spark.implicits._

    val tbDate_csv = "D:\\hadoop\\sparkSql\\csvData\\tbDate.csv"
    val tbStockDetail_csv = "D:\\hadoop\\sparkSql\\csvData\\tbStockDetail.csv"
    val tbStock_csv = "D:\\hadoop\\sparkSql\\csvData\\tbStock.csv"

    def loadCSV(csvPath: String): DataFrame = {
        val df: DataFrame = spark.read.format("csv").option("delimiter", ",") // 分隔符，默认为逗号,
                .option("header", "true") // true:首行作为标题  false:首行作为数据内容(默认为false)
                //.option("quote", "")            //引号字符，默认为双引号"
                .option("nullValue", "\\N") //指定一个字符串代表 null 值
                .option("inferSchema", "true") // true:自动推测字段类型 ,false:使用String类型  默认为false
                .load(csvPath)
//        df.show()
//        df.printSchema()
//        println("================")
        df
    }

    def tbDateDF: DataFrame = {
        loadCSV(tbDate_csv)
    }

    def tbStockDetailDF: DataFrame = {
        loadCSV(tbStockDetail_csv)
    }

    def tbStockDF: DataFrame = {
        loadCSV(tbStock_csv)
    }
    def createTab(): Unit ={
        tbDateDF.createOrReplaceTempView("tbDate")
        tbStockDetailDF.createOrReplaceTempView("tbStockDetail")
        tbStockDF.createOrReplaceTempView("tbStock")
    }
}

object Load_Data {
    //测试
    def main(args: Array[String]): Unit = {
        val data: Load_Data = new Load_Data()

        data.tbDateDF
        data.tbStockDetailDF
        data.tbStockDF
    }
}
