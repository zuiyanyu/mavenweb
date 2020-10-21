package 隐式转换

object ImplicitTest3 {
    def main(args: Array[String]): Unit = {
        //这种方式可以为入参设置一个初始值，但是固定死的值。
        def test0(  name :String = "固定值" ): Unit ={
            println(s"入参为 $name")
        }

        //TODO Scala 允许参数进行隐士转换
        //TODO 进行入参的隐士转换，动态为入参设置一个初始值，是动态可变的。比如从数据库中查询出来的值。
        def test(implicit name :String="固定值" ): Unit ={
            println(s"入参为 $name")
        }
        test
        test("李四")

        //可以是从库中查询出来的动态值
        implicit val name  = "张三" ;
        test
    }

}
