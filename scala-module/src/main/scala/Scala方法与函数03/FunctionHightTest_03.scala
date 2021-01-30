package Scala方法与函数03

/**
  * 函数编程 - 高级   可变参数与调用； 参数设置默认值
  */
object FunctionHightTest_03 {
    def main(args: Array[String]): Unit = {

        //TODO 一个 参数
        def f1(name :String )={
            println(s"f1 : name = $name");
        }
        f1("战三"); // f1 : name =战三

        //TODO 方法可变参数   在参数类型后面加*,表示可变参数
        def f2(names :String* )={
            println(s"f2 : names = $names");
        }
        f2("战三","李四"); //f2 : names =WrappedArray(战三, 李四)

        //TODO  可变参数 必须放置在参数列表的最后
        def f3( age :Int,names :String*  )={
            println(s"f3 : names = $names , age = $age ");
        }
        f3(20,"战三","李四"); //f2 : names =WrappedArray(战三, 李四)

       // TODO 参数设置默认值
        def f4( age :Int = 20,name :String ="战三" )={
            println(s"f4 : names = $name , age = $age ");
        }
        f4();  //f4 : names = 战三 , age = 20
        f4(12);//f4 : names = 战三 , age = 12
        f4(name= "王五");//带名参数 //f4 : names = 王五 , age = 20
        f4(12,"李四"); // f4 : names = 李四 , age = 12
//        f4(null,null); //报错
    }
}
