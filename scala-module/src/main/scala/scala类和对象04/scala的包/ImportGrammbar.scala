package scala类和对象04.scala的包

//0.  默认情况下，Scala 总会引入 java.lang._ 、 scala._ 和 Predef._，这里也能解释，为什么以scala开头的包，在使用时都是省去scala.的原因。

//1. 方式1   直接引入某个类
import scala.collection.mutable.Map

//方式2  引入包内所有成员
import java.awt._

//方式3   有选择的引入多个成员
import java.util.{ArrayList,Set}

// 方式4  包的重命名
import java.util.{HashMap => JavaHashMap}


// 方式5：  scala可以将不想使用的类给隐藏掉
//import java.util.{HashMap => _,_}  //隐藏成员，即排除成员: 引入了util包的所有成员，但是HashMap被隐藏了

//方式6  scala为了防止使用包的时候差生误会，可以使用绝对报名
// var map = new  _root_.java.util.HashMap()

//自定义的相对包名，使用 java.util.HashMap会找此相对包名下的HashMap 即 scala类和对象.scala的包.java.util.HashMap，而不是绝对包名。
//package java.util{
//  class HashMap{}
//}

object ImportGrammbar {
  def main(args: Array[String]): Unit = {
    //java类
    var javaMap:JavaHashMap[String,String] = new JavaHashMap();
    javaMap.put("userName" ,"张三") ;
    javaMap.put("userName1","张三1") ;
    javaMap.put("userName2","李四2") ;
    println("javaMap = " + javaMap);
    println("javaMap.get(\"userName\")) = " + javaMap.get("userName"));

    println("======================")
    //scala定义的map
    //默认情况下 Scala 使用不可变 Map。如果你需要使用可变集合，你需要显式的引入 import scala.collection.mutable.Map 类
    var scalaMap:Map[String,Int] = Map()
    scalaMap += ("age" ->10);
    scalaMap += ("age1"->11);
    scalaMap += ("age2"->12);
    println("scalaMap = " + scalaMap);
    println("scalaMap(\"age\") = "+ scalaMap("age"));

    println("======================")
    val symbol = Symbol("张三")
    println("Symbol="+ symbol)

    val symbol2 = Symbol("张三2")
    println("Symbo2="+ symbol2)


  }
  def handler(evt: event.ActionEvent) { // java.awt.event.ActionEvent
    // 因为引入了java.awt，所以可以省去前面的部分
  }

  // 定义一个样例类。
  // 这个private是干嘛的？ 是表示这个类的构造方式是私有化的。
  final case class Symbol private (name: String) {
    override def toString: String = "'" + name
  }
}
