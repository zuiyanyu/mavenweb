package scala集合.Map集合

import scala.collection.immutable

object Map_不可变 {
  def main(args: Array[String]): Unit = {
//    function
//    add
//    query
//    define
    filter

  }
  //TODO 4.  功能介绍
  def filter(): Unit = {
    println("=========TODO 5.  过滤key ===============")
    val map = Map("a" -> 1, "b" -> 2)
    // false 就不保留，true就进行保留
    val newMap: Map[String, Int] = map.filterKeys(_ => false)
    println(newMap)
  }
  //TODO 4.  功能介绍
  def function(): Unit ={
    println("=========TODO 4.  功能介绍===============")
    val map = Map("a"->1,"b"->2)

    //更新元素值   产生一个新的map集合
    val map2: Map[String, Int] = map.updated("a",233)
    this.print_(map)  //(a,1) (b,2)
    this.print_(map2)  //(a,233) (b,2)

    //删除一个元素
    val map3: Map[String, Int] = map - "a"
    this.print_(map)  //(a,1) (b,2)
    this.print_(map3)  //(b,2)
  }

  //TODO 3. 元素添加
  def add(): Unit ={
    println("==========TODO 3. 元素添加==============")
    val map = Map("a"->1,"b"->2)

    //方式1
    //val map2_1: Map[String, Int] = map + "c"->3  //编译不通过
    val map2: Map[String, Int] = map + ("c"->3)
    this.print_(map2)  // (a,1) (b,2) (c,3)

    //方式2
    //val map3_1: Map[String, Int] = map + ("c",3) //编译不通过
    //val map3_1: Map[String, Int] = map + ("c",3) //编译不通过
    val map3: Map[String, Int] = map + (("c",3))
    this.print_(map3)  // (a,1) (b,2) (c,3)

    val b = immutable.Map.newBuilder[String, List[Int]]
        b += ("a"->List(1))
        b += ("b"->List(2,3,4))
    val test: Map[String, List[Int]] = b.result()
    println(test)  //Map(a -> List(1), b -> List(2, 3, 4))

    val b2 = immutable.Map.newBuilder[String,Int]
    b2 += ("a"->1)
    b2 += ("b"->2)
    val test2: Map[String,Int] = b2.result()
    println(test2)  //Map(a -> 1, b -> 2)

  }
  //TODO 2.  遍历方式
  def query(): Unit ={
    println("=========TODO 2.  遍历方式===============")
    val map = Map("a"->1,"b"->2)

    //方式1  获取某一个元素
     val optA: Option[Int] = map.get("a")
     val optC: Option[Int] = map.get("c")
    println("optA = " + optA)  // optA = Some(1)
    println("optC = " + optC)  // optC = None

    val valueA: Int = optA.getOrElse(-1)
    val valueC: Int = optC.getOrElse(-1)
    println(s"valueA = $valueA")  // valueA = 1
    println(s"valueC = $valueC")  // valueC = -1
  }
  //TODO 1.  声明方式
  def define(): Unit ={
    println("==========TODO 1.  声明方式==============")
    //方式1
    val map: Map[String, Int] = Map(("a",1),("b",2))
    this.print_(map)  //(a,1) (b,2)

    //方式2
    val map2 = Map("a"->1,"b"->2)
    this.print_(map2) // (a,1) (b,2)

    //方式3
    val b2 = immutable.Map.newBuilder[String,Int]
    b2 += ("a"->1)
    b2 += ("b"->2)
    val map3: Map[String,Int] = b2.result()
    println(map3)  //Map(a -> 1, b -> 2)


  }
  def print_[K,V](map:Map[K,V]): Unit ={
//    println(map.mkString("\t"))
    for (elem <- map) {
      print(s"$elem ")
    };println;
  }
}
