package sparkSql.dataFrame.DF_DSL

object DF_Opts extends DF_Data {
    def main(args: Array[String]): Unit = {
        map
    }

    def map(): Unit ={
//        df.show();
//        df.map(row=>{
//
//        })
    }
    //基本统计量
    def describe(){
        df.describe().show()
    }
}
