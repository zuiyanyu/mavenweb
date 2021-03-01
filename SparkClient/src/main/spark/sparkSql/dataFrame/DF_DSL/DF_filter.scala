package sparkSql.dataFrame.DF_DSL

import org.apache.spark.api._
import org.apache.spark.api.java.function._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Dataset, Row}

object DF_filter extends DF_Data {

    import spark.implicits._

    //1. 入参为：Column 过滤条件
    //TODO 逻辑运算符：>, <, ===
    def df_filter_condition_01(): Unit = {
        df.show()
        val filterDF: Dataset[Row] = df.filter($"age" > 20)
        filterDF.show()

        //值等于 判断
        df.filter($"age" === 20).show() // 方式1
        df.filter(_.getInt(1) == 21).show() // 方式2

    }
    //TODO  传递参数过滤
    def df_filter_condition_02: Unit ={
        val age:Int=20;
        df.filter($"age"===age).show
        df.filter($"age">age).show

    }
   //TODO 其他各种方式的过滤
    def df_filter_condition_03: Unit ={
        //TODO 对字符串过滤
        println("=============1================")
        //过滤以 2结尾的名字
        df.filter($"name".endsWith("2")).show

        println("=============2================")
        //过滤以 2结尾的age值
        df.filter($"age".endsWith("2")).show

        println("=============3================")
        //字符串 等于 某个值
        //TODO  equalTo 返回的是 Column 而不是boolean
        df.filter($"name".equalTo("zhangsan")).show()

        println("=============4================")
        //TODO 传递参数过滤
        val name = s"zhangsan"
        df.filter($"name"equalTo(name)).show()

        println("=============5================")
        //TODO 当dataframe没有字段名时，可以用默认的字段名[_1, _2, .....]来进行判断
        df.filter($"_1"equalTo(name)).show()

        println("=============6================")
        //TODO 多条件判断 逻辑连接符 &&（并）、||（或）
        df.filter($"age"===21 && $"name".equalTo(name)).show
        df.filter($"age"===1 || $"name".endsWith("2")).show

    }

    //2. 入参为：字符串表达式
    //TODO 逻辑运算符：>, <, ===
    def df_filter_conditionExpr(): Unit = {
        val filterDF: Dataset[Row] = df.filter("age>20")
        filterDF.show()
    }


    //3. df.where 等价于 df.filter
    def df_where(): Unit = {
        //df.where 等价于 df.filter
        df.where($"age" > 20).show()
        df.where("age>20").show()

    }

    //4.入参是一个 函数
    def df_filter_function_01 = {
        //TODO 在函数里面可以做更多的操作
        def func(row: Row): Boolean = {
            //获取每一行的字段信息
            val schema: StructType = row.schema
            val names: Array[String] = schema.fieldNames

            //获取要操作列的位置
            val fieldName = "age";
            val index: Int = names.indexOf(fieldName)

            //val value = row(index)
            //val ageValue: Int = value.toString.toInt
            val ageValue: Int = row.getInt(index)
            println(s"name=${names(index)},value =${ageValue} ")

            return ageValue == 21;
        }

        //这里要调用函数
        val filterDF: Dataset[Row] = df.filter(func(_))
        filterDF.show()


    }

    def df_filter_function_02 = {
        //TODO 只能做些简单判断
        df.filter(_.getInt(1) == 21).show() // 简单方式  入参是func
        //df.filter($"age"===20).show()           // 简单方式 入参是condition表达式
    }

    /**
      * Caused by: java.io.NotSerializableException: java.lang.Object
      * Serialization stack:
      * 	- object not serializable (class: java.lang.Object, value: java.lang.Object@77ecdc2b)
      */
    def df_filter_function_error: Unit = {
        //TODO 这种方式报错：
        val filterDF: Dataset[Row] = df.filter(row=>{
            return true;
        })
        filterDF.show()
    }
    def main(args: Array[String]): Unit = {
        df_filter_function_error
    }

}
