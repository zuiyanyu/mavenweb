package Scala方法与函数03

object FunctionSupperTest_04 {
    def main(args: Array[String]): Unit = {
        //TODO scala是完全面向函数的语言，scala中的类也是一个函数
       // TODO 所以函数可以当成参数，当成类，当成返回值类型 等等

        println("==================1======================")

        def f0(age : Int ):Int = {
            println(s"comming into f0: age = $age");
            age ;
        }
        def f0_1() = {
            println(s"comming into f0_1");
        }
        //TODO 将函数当成参数
        //TODO 将符合要求的函数作为参数传递给另外一个函数
        def f1(f:(Int) =>Int){
            // 使用传递进来的函数
             f(20);
        }
        f1(f0);  // f0: age = 20
        //TODO 匿名函数
        f1( (age:Int ) =>{  println(s"f0: age = $age"); age ;});
        println("==================2======================")

        //TODO 方法作为返回值 返回
        def f2()={
            //TODO 方法会执行，返回执行结果 ()
            f0_1
        }
        def f3()={
            //TODO 在方法名后面加 下划线，表示不执行方法，把方法当成一个类型返回
            f0_1 _
        }

        println(" f2() = "+ f2());  //  f2() = ()
        println(" f3() = "+ f3());  //  f3() = <function0>
        println("----------------- ")
        val fun3 = f3()  //返回的函数被接收
        fun3() ;  // comming into f0_1
        //上面两步等价于
        f3()() ;  //comming into f0_1
        println("===================3=====================")

        //TODO 如果不使用  f0 _， 也想返回函数引用，那么就声明函数返回值为 fo形式的函数。
        def f4():Int=>Int ={
            f0
        }
        def f4_1():Int=>Int ={
            //如果指定了返回类型为函数，那么 _ 可省可不省略(建议别省略)
            f0 _
        }
        //等价于 下面的效果
        //简写为
        def f5() ={
            f0 _
        }
//        def f6() ={
//            f0  //方法调用，少参数报错  类型推断不知道要返回的是函数本省，还是函数运行的结果。 因为 调用时候：f0()可简写为 f0
//        }
        f4()(20) ;  //comming into f0: age = 20
        f5()(30) ;  //comming into f0: age = 30
        println("=================4=======================")

        //TODO 函数柯里化 （本质是闭包实现的）  把一个完整的逻辑拆成一段一段的
        //   好处在隐士转换中体现   类型的隐士转换
        def f6(name:String )(age : Int =20): Unit ={
            println(s" comming into f6: name=$name , age = $age")
        }
        f6("李四")(10);  //  comming into f6: age = 10
        f6("李四")()
        println("=================5=======================")
        //TODO 闭包   改变了局部变量的声明周期，将变量包含到当前函数的内部，形成一个闭包的效果。
        def f7(name:String ) ={
            // 先定义后返回
            def f7_inner(age:Int): Unit ={
                println(s" comming into f7:name=${name}  , age = $age")

            };
            f7_inner _  ;
        }
        //简化
        def f8(name: String ) ={
            //直接返回此匿名函数
            (age: Int)=>{
                println(s" comming into f8:name=${name}  , age = $age")
            } ;
        }
        //扩展  返回值是一个函数  ，类型为 (String,Int)=>String
        def f9():(String,Int)=>String ={
            //直接返回此匿名函数
            (name: String ,age: Int) =>{
                val retStr =  s"comming into f8:name=${name}  , age = ${age}" ;
                println(retStr)
                retStr ;
            } ;
        }
        f7("张三")(20); // comming into f7:name=张三  , age = 20
        f8("李四")(25); //  comming into f8:name=李四  , age = 25
        f9()("张三", 20) ;      // comming into f8:name=张三  , age = 20


        println("============================================")
        println("================6============================")
        //匿名函数详解
        def f10(f:(String)=>Unit)={
            f("abcd");
        }
        //简化
        def f11(f: String => Unit)={
            f("abcd");
        }
        f10((name:String)=>{println("name="+name)});
        //简化1
        f10((name:String)=>println("name="+name));
        //简化2
        f10((name)=>println("name="+name));
        //简化3
        f10(name=>println("name="+name));
        //简化5
        f10(name=>println(name));
        f10(println(_));

    }
}
