package 模式匹配

import scala.collection.mutable.ArrayBuffer

object d模式匹配_所有类型匹配 {
    def main(args: Array[String]): Unit = {
        //TODO 类型匹配：可以匹配对象的任意类型，这样就避免了使用isInstanceOf 和 asInstanceOf 方法
        exam10
    }
    //TODO case语句的中置(缀)表达式
    /**
      * 什么是中置表达式？1 + 2，这就是一个中置表达式。\
      * 如果unapply方法产出一个元组，你可以在case语句中使用中置表示法。比如可以匹配一个List序列
      */
    def exam10(): Unit ={
        var list = List(1, 3, 5, 9,10,11)
        val result=list match {
            case first :: second :: three::rest =>first + "**" + second + "**" + three+"**"+rest
            case _ => "匹配不到..."
        }
        println(result)// 1**3**5**List(9, 10, 11)

    }
    //TODO 9.样例类的模式匹配
    /**
      * 1. 样例类用case关键字进行声明。
      * 2. 样例类是为模式匹配(对象)而优化的类
      * 3. 在样例类对应的伴生对象中提供apply方法让你不用new关键字就能构造出相应的对象
      * 4. 提供unapply方法让模式匹配可以工作
      *
      * 当我们有一个类型为Amount的对象时，可以用模式匹配来匹配他的类型，
      * 并将属性值绑定到变量(即：把样例类对象的属性值提取到某个变量)
      */
    def exam09(): Unit ={
        abstract class Amount
        case class Dollar(value: Double) extends Amount
        case class Currency(value: Double, unit: String) extends Amount
        case object NoAmount extends Amount

        val array: Array[Amount] = Array(
            Dollar(1000.0),  // Dollar(1000.0): Dollar(v) => v = 1000.0
            Currency(1000.0, "RMB"),  // Currency(1000.0,RMB): Currency(v, u) => v=1000.0,u=RMB
            NoAmount // NoAmount: NoAmount => 无参样例类
        )

        for (amt <- array) {
            val result = amt match {
                case Dollar(v) => s"Dollar(v) => v = $v"
                case Currency(v, u) => s"Currency(v, u) => v=$v,u=$u"
                case NoAmount => "NoAmount => 无参样例类"
                case _ => "没有匹配上"
            }
            println(amt + ": " + result)
        }

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
    //TODO 7.变量声明中的模式
    /**
      * match中每一个case都可以单独提取出来，意思是一样的.
      */
    def exam07(): Unit ={
        val (x, y) = (1, 2)
        val (q, r) = BigInt(10) /% 3  // 包含了2个连续的运算符

        println("x = "+ x) // x = 1
        println("y = "+ y) // y = 2
        println("q = "+ q) // q = 3
        println("r = " + r) // r = 1

        val arr = Array(1, 7, 2, 9)
        val Array(first, second, _*) = arr
        println(first, second) // (1,7)
    }
    //TODO 6. 对象匹配_2  进阶
    /**
      * 1. 当case 后面的对象提取器方法的参数为多个，则会默认调用def unapplySeq() 方法
      *
      * 2. 如果unapplySeq返回是Some，获取其中的值,判断得到的sequence中的元素的个数是否是三个,
      * 如果是三个，则把三个元素分别取出，赋值给first，second和third
      *
      * 3.如果不是三个，那么就无法匹配。匹配的是 case _ 。
      *
      * 3. 其它的规则不变.  同 对象匹配_1 的规则
      */
    def exam06(): Unit ={
        object Names {
            // unapplySeq   返回参数有多个，即返回参数是集合
            def unapplySeq(str: String): Option[List[String]] = {
                if (str.contains(",")) {
                     val array: Array[String] = str.split(",")
                     val list: List[String] = array.toList
                      println("list = "+list)
                     Some(list)
                } else{
                    None
                }
            }
        }

        var namesString = "Alice,Bob,Thomas"  // Names(first, second, third) =>( first= Alice ，second= Bob, third= Thomas )
            namesString = "Alice,Bob,Thomas,a"  //_ => nothing matched
        val result = namesString match {
            case Names(first, second, third) => { //多个参数接收返回值，会调用unapplySeq 提取器
               s"Names(first, second, third) =>( first= $first ，second= $second, third= $third )"
            }
            case _ => "_ => nothing matched"
        }
        println(result)


    }
    //TODO 5. 对象匹配_1
    /**
      * 对象匹配，什么才算是匹配呢？，规则如下:
      * 1. case中对象的unapply方法(对象提取器)返回Some集合则为匹配成功
      * 2. 返回none集合则为匹配失败
      * 3. 如果匹配成功， 即返回的是Some集合，则unapply提取器返回的结果会返回给n这个形参
      */
    def exam05(): Unit ={
        object IntToString {
            //将int类型 转为String
            def apply (i: Int): String = i.toString

            //apply的反向操作：将String转为Int
            def unapply(s: String): Option[Int] = {
                try{
                    Some(s.toInt+1)  //如果能转成功 返回 Some
                } catch {
                    case e:Exception =>None   //失败则返回 None
                }
            }

        }
        // 模式匹配使用：
        /**
          * 1. 构建对象时apply会被调用 ，比如 val n1 = Square(5)
          *
          * 2. 当将 Square(n) 写在 case 后时[case Square(n) => xxx]，会默认调用unapply 方法(对象提取器)
          *
          * 3. number 会被 传递给def unapply(z: Double) 的 z 形参
          *
          * 4. 如果返回的是Some集合，则unapply提取器返回的结果会返回给n这个形参
          *
          * 5. case中对象的unapply方法(提取器)返回some集合则为匹配成功
          *
          * 6. 返回none集合则为匹配失败
          */
        val number: String = "12"  // IntToString(n) =>number= 12 : n= 13
        number match {
            case IntToString(n) => println(s"IntToString(n) =>number= $number : n= $n")
            case _ => println("nothing matched")
        }
    }
    //TODO 4. 匹配元组
    /**
      * 匹配元组：原则类似匹配数组  参考：exam2()
      */
    def exam04(): Unit ={
        val array = Array(
                (0, 1),       //(0, _)  =>  (0,1)
                (1, 0),       //(y, 0) =>  (1,0)
                (2, 1),       //(a,b)  =>  (2,1) :(a=2,b=1)
                (1,0,2)      //(a,b,c) =>  (1,0,2) :(a=1,b=0，c=2)
        )

        for (pair <- array) {
            val result = pair match {
                case (0, _) => s"(0, _)  =>  $pair"
                case (y, 0) => s"(y, 0)  =>  $pair"
                case (a, b) =>  s"(a,b)  =>  $pair :(a=$a,b=$b)"
                case (a,b,c)=> s"(a,b,c) =>  $pair :(a=$a,b=$b，c=$c)"
                case _      => s"_       =>  $pair"
            }
            println(result)
        }
    }
    //TODO 3.匹配列表
    /**
      * 匹配列表：原则类似匹配数组  参考：exam2()
      */
    def exam03(): Unit ={
        val array = Array(
            List(0),        // 0 :: Nil => List(0)
            List(1, 0),     // x :: y :: Nil=>List(1, 0) : (x= 1 ,y=0)
            List(0, 1, 2),  // 0 :: tail => List(0, 1, 2)
            List(1, 0, 0)   // something else
        )
        for (list <-array) {
            val result = list match {
                case 0 :: Nil => s"0 :: Nil => $list"     //匹配只有一个 0 元素的list集合
                case x :: y :: Nil => s"x :: y :: Nil=>$list : (x= $x ,y=$y)"   //匹配只有两个元素的list集合，并赋值
                case 0 :: tail => s"0 :: tail => $list"   //匹配第一个元素是0的list集合
                case _ => "something else"    //
            }
            println(result)
        }

    }
    //TODO 2.匹配数组
    /**
      * Array(0)    匹配只有一个元素且为0的数组。
      * Array(x,y)  匹配数组有两个元素，并将两个元素赋值为x和y。当然可以依次类推Array(x,y,z) 匹配数组有3个元素的等等....
      * Array(0,_*) 匹配数组以0开始
      */
    def exam02(): Unit ={
        val array = Array(
            Array(0),              // result = Array(0) => [I@4c203ea1
            Array(1, 0) ,          //result = Array(x, y)=>[I@4c203ea1 :x=1 y=0
            Array(0, 1, 0),        //result =  Array(0, _*) => [I@4c203ea1
            Array(1, 1, 0),        //"什么集合都不是"
            Array(1, 1, 0, 1),     //"什么集合都不是"
            Array(0, 2)            // result = Array(x, y)=>[I@4c203ea1 :x=0 y=2
        )

        for (arr <- array) {
            val result = arr match {
                case Array(0)     => s"Array(0) => $arr"
                case Array(x, y)  => s"Array(x, y)=>$arr :x=$x y=$y"
                case Array(0, _*) => s" Array(0, _*) => $arr"
                case Array(_,2)   => s"Array(_,2) => $arr"
                case _ => "什么集合都不是"
            }
            println("result = " + result)
        }

    }
    //TODO 1. 类型匹配基本示例
    def exam01(){
        val a =8 ;
        val obj  =
            if(a == 1) 1                     //1 Int=>1
            else if(a == 2) "2"             //2 String=>2
            else if(a == 3) BigInt(3)       //3 BigInt=>3
            else if(a == 4) Map("aa"->2)   //4 Map[String,Int]=>Map(aa -> 2)  //map 里面存储的是对偶，map类型与key ,value的类型无关
            else if(a == 5) Map(2->"aa")   //5 Map[String,Int]=>Map(2 -> aa)  //map 里面存储的是对偶，map类型与key ,value的类型无关
            else if(a == 6) Array(1,2,3)   //6 Array[Int]=>[I@75bd9247
            else if(a == 7) Array("aa",1)  //7 Array[Any]=>[Ljava.lang.Object;@129a8472
            else if(a == 8) Array("aa")    //8 Array[String]=>[Ljava.lang.String;@75bd9247


        obj match{
            case a :Int              =>println(s"$a Int=>$a")
            case b :String           =>println(s"$a String=>$b")
            case c :BigInt           =>println(s"$a BigInt=>$c")
            case d :Map[String,Int]  =>println(s"$a Map[String,Int]=>$d")
            case e :Map[Int,String]  =>println(s"$a Map[Int,String]=>$e")
            case f :Array[Int]       =>println(s"$a Array[Int]=>$f")
            case g :Array[String]    =>println(s"$a Array[String]=>$g")
            case h :Array[Any]       =>println(s"$a Array[Any]=>$h") // 7  Array[Any]=>[Ljava.lang.Object;@129a8472
            case _                   =>println("没有匹配！")
        }
    }

}
