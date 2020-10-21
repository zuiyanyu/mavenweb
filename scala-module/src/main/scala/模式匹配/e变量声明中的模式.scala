package 模式匹配

object e变量声明中的模式 {
    def main(args: Array[String]): Unit = {
        //TODO 变量声明中的模式
        //TODO match中每一个case都可以单独提取出来，意思是一样的.
        /**
          * match中每一个case都可以单独提取出来，意思是一样的.
          */
        def exam07(): Unit ={
            val (x, y) = (1, 2)
            val (q, r) = BigInt(10) /% 3  // 包含了2个连续的运算符

            println("q = "+ q)
            println("r = " + r)

            val arr = Array(1, 7, 2, 9)
            val Array(first, second, _*) = arr
            println(first, second)
        }
    }

}
