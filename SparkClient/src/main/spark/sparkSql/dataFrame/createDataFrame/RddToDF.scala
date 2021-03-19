package sparkSql.dataFrame.createDataFrame

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

/**
  *  TODO 从一个存在的RDD进行转换成DataFrame；
  */
object RddToDF {

    private val conf = new SparkConf().setAppName("").setMaster("local[2]")
    private val spark:SparkSession = SparkSession.builder().config(conf).getOrCreate()
    private val sc =spark.sparkContext
    import spark.implicits._

    //TODO 1. 通过编程的方式 将rdd转换为DataFrame (好处就是可以指定每一列的数据类型)
    def rdd_To_DF_01: Unit ={
        //1. 创建DF中的数据 （rdd的形式）
        val rdd: RDD[(String, Int)] = sc.parallelize(Array(("zhangsan",20),("zhangsan2",21),("zhangsan3",22),("zhangsan4",23)))
        //val rowRDD: RDD[Row] = rdd.map(data=>Row(data._1,data._2))
        val rowRDD: RDD[Row] = rdd.map{case (name,age)=>Row(name,age)}

        //2. 创建DF中的schema信息
        //val structType =  StructType(Array(StructField("name",StringType,false),StructField("age",IntegerType,false)))
        val structType =  StructType( StructField("name",StringType,false)::StructField("age",IntegerType,false)::Nil)

        //TODO 3. 将DF的rdd数据 和 schema结合起来，形成一个完整DF
        val df: DataFrame = spark.createDataFrame(rowRDD,structType)


        //4. 打印df的数据，进行验证
        df.show()
    }
    //TODO 2. 通过反射确定（需要用到样例类）
    /*
       解决value toDF is not a member of org.apache.spark.rdd.RDD[People]
       做到以下两点:
           1. import sqlContext.implicits._ 语句需要放在获取sqlContext对象的语句之后
           2. case class People(name : String, age : Int) 的定义需要放在方法的作用域之外（即Java的成员变量位置）
     */
    //2.用样例类进行代替 DF中的schema信息
    case class People(name:String,age:Integer)
    def rdd_To_DF_02: Unit ={

        //1. 创建DF中的数据 （rdd的形式）
        val rdd: RDD[(String, Int)] = sc.parallelize(Array(("zhangsan",20),("zhangsan2",21),("zhangsan3",22),("zhangsan4",23)))

        //3. 将rdd中的数据封装到样例类中
        val peopleRDD: RDD[People] = rdd.map{case (name,age)=>People(name,age)}
        peopleRDD.collect().foreach(println(_))

       //TODO 4. 将DF的rdd数据 和 schema结合起来，形成一个完整DF
        import spark.implicits._
        val df: DataFrame = peopleRDD.toDF
        df.schema.foreach(println)

       //5. 打印df中的数据内容
        df.show()
    }
    //TODO 3.通过手动确定转换 (这种方式会自动推断数据类型) (需要导入隐士转换包)
    def rdd_To_DF_03: Unit ={

        //1. 创建DF中的数据 （rdd的形式）
        val rdd: RDD[(String, Int)] = sc.parallelize(Array(("zhangsan",20),("zhangsan2",21),("zhangsan3",22),("zhangsan4",23)))

        //TODO 2. rdd直接进行转换成DF
        import spark.implicits._
        val df: DataFrame = rdd.toDF("name","age")

        //3. 显示数据
        /*
        StructField(name,StringType,true)
        StructField(age,IntegerType,true)
         */
        df.schema.foreach(println)
        df.show()
    }
    def main(args: Array[String]): Unit = {
        //rdd_To_DF_01
        rdd_To_DF_02
        //rdd_To_DF_03
    }
}
