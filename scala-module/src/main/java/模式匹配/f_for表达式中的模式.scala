package 模式匹配

object f_for表达式中的模式 {
    def main(args: Array[String]): Unit = {
        exam08
    }
    //TODO 8. for表达式中的模式
    def exam08(): Unit ={
        val map = Map("A"->1, "B"->0, "C"->3)
        for ( (k, v) <- map ) {
            println(k + " -> " + v)
        }

        for ((k, 0) <- map) {
            println(k + " --> " + 0)
        }

        for ((k, v) <- map if v == 0) {
            println(k + " ---> " + v)
        }

    }
}
