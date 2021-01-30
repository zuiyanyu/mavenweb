package scala流程控制.scala_if

object Scala_if {
    def main(args: Array[String]): Unit = {
        if_else_01

    }


    /**
      * 1. if-else 会有返回值  ，会返回最后一行语句的 运行结果
      * 2. if 语句也会有返回值
      */
    def if_else_01(): Unit ={
        var age1 =if(true){20}else{30}
        println(age1)

        // 简写：只有一个语句体的时候，可以省略 {}
        //scala 中没有三元运算符，可以使用下面这种简写形式替换
        var age2 =if(true) 20 else 30
        println(age2)

        var age3 = if(true) 20 ;
        println(age3)

        //如果没有返回值,那么返回值用:() 代替
        var age4 = if(false) 20
        print(age4)  //()
    }


}
