package sparkSql.dataFrame.DF_DSL

import org.apache.spark.sql.types.{StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

object DF_Schema {

    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()
    //TODO  获取dataFrame的schema信息
    def dataFrame_schema: Unit ={
        //1. 读取json文件，创建DF
        val jsonPath = "D:\\hadoop\\sparkSql\\sparkRead\\peopleJson.txt"
        val jsonDataFrame: DataFrame = spark.read.json(jsonPath)

        //2. 获取DF的schema
        //方式1：
        val schema: StructType = jsonDataFrame.schema
        println(schema) //StructType(StructField(age,StringType,true), StructField(name,StringType,true))

        val fieldList: List[StructField] = schema.toList

        for (field <- fieldList) {
            println("==========================")
            println("field.name = "+field.name)  //field.name = age
            println("field.dataType = "+field.dataType) // field.dataType = StringType
            println("field.metadata = "+field.metadata) //field.metadata = {}
            println("field.getComment = "+field.getComment()) //field.getComment = None
            println("field.toString() = "+field.toString())//field.toString() = StructField(age,StringType,true)
        }
        val schema2: String = schema.mkString(",")  //schema2 = StructField(age,StringType,true),StructField(name,StringType,true)
        println("schema2 = "+schema2)

        //TODO 很有用
        val fieldNames: Array[String] = schema.fieldNames
        val names: String = fieldNames.mkString(",")
        println("names = "+names);  // names = age,name

        //方式2
        //TODO 很有用
        val columns: Array[String] = jsonDataFrame.columns
        val columnsStr: String = columns.mkString(",")
        println("columnsStr = "+columnsStr) //columnsStr = age,name

        //打印schema
        println("---------------")
        jsonDataFrame.printSchema
        /*
            root
             |-- age: string (nullable = true)
             |-- name: string (nullable = true)
         */
    }
    def main(args: Array[String]): Unit = {
        dataFrame_schema
    }
}
