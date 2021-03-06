package 隐式转换

object ImplicitTest3 {
    def main(args: Array[String]): Unit = {
        //这种方式可以为入参设置一个初始值，但是固定死的值。
        def test1(  name :String = "固定值1" ): Unit ={
            println(s"入参为 $name")
        }
        test1()  //()不能省略  //入参为 固定值1

        //TODO Scala 允许参数进行隐士转换
        //TODO 进行入参的隐士转换，动态为入参设置一个初始值，是动态可变的。比如从数据库中查询出来的值。
        //取值顺序为：先取入参值，再取隐士值，最后取默认值
        def test2(implicit name :String = "固定值2" ): Unit ={
            println(s"入参为 $name")
        }
        test2  // 入参为 固定值2
        test2("李四") //入参为 李四

        //TODO 2.方法参数隐士转换
        //可以是从库中查询出来的动态值
        implicit val name  = "张三" ;
        test2 // 入参为 张三
    }

}
