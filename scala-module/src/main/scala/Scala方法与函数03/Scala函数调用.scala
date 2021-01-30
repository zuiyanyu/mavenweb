package Scala方法与函数03

object Scala函数调用 {


    //TODO Scala 函数传名调用(call-by-name)
    /**
    * Scala的解释器在解析函数参数(function arguments)时有两种方式：
    *     1.传值调用（call-by-value）：先计算参数表达式的值，再应用到函数内部；
    *     2.传名调用（call-by-name）：将未计算的参数表达式直接应用到函数内部 , 在函数内部进行参数表达式的值计算的。
    *     这就造成了一种现象，每次使用传名调用时，解释器都会计算一次表达式的值。
    */
    /**
      *
      * @param time  del
      */
    def delayed(time: => Long)={
        println("comming into delayed !");
        println("参数：time= "+ time );
    }
    def time():Long={
        3L ;
    }


    def main(args: Array[String]): Unit = {
        val t = ()=>{3L ;}
        delayed(time);
        delayed(t());
        delayed((()=>{3L;})())
    }
}

