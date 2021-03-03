package sparkSql.dataFrame.DF_DSL

object DF_Opts extends DF_Data {
    def main(args: Array[String]): Unit = {
        describe
    }
    //基本统计量
    def describe(){
        df.describe().show()
    }
}
