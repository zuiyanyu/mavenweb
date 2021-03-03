package sparkSql.dataFrame.DF_DSL

import org.apache.spark.sql.{SaveMode, functions}
object DF_Select extends DF_Data{
    import spark.implicits._
    // 1.只查看”name”列数据  三种方式
    def df_select(): Unit ={
        df.select("name").show()
        df.select('name).show()
        df.select($"name").show()
    }
    //查看”name”列数据以及”age+1”数据
    def df_select_02(): Unit ={
        df.select($"name", $"age" + 1).show()

        df.withColumn("age",$"age"+1)
                .select("name","age")
                .show();
    }
    //给列取别名
    def df_select_03: Unit ={
        df.select(functions.expr("name as newName")).show()
        val append: SaveMode = SaveMode.Append

    }


    def main(args: Array[String]): Unit = {
        //df_select
        df_select_03
    }
}
