package scala集合.Map集合

import scala.collection.mutable

object Map_可变 {
  def main(args: Array[String]): Unit = {
    function
    add
    query
    define

  }
  //TODO 4.  功能介绍
  def function(): Unit ={
    println("=========TODO 4.  功能介绍===============")
    val map = mutable.Map("a"->1,"b"->2)

    //更新元素值   产生一个新的map集合
    val map2: mutable.Map[String, Int] = map.updated("a",233)
    this.print_(map)   //(b,2) (a,1)
    this.print_(map2)  //(b,2) (a,233)


    //更新元素  原始集合修改
    map.update("b",789)
    this.print_(map)  //(b,789) (a,1)

    map.put("a",2221)
    this.print_(map) //(b,2) (a,2221)

    map += ("a"->100001)
    this.print_(map) //(b,789) (a,100001)
    println("-------------")


    //删除一个元素
    val map3: mutable.Map[String, Int] = map - "a"
    this.print_(map)  //(a,1) (b,2)
    this.print_(map3)  //(b,2)

    //删除一个元素
    map -= "b"
    this.print_(map) //(a,1)
  }

  //TODO 3. 元素添加
  def add(): Unit ={
    println("==========TODO 3. 元素添加==============")
    val map =  mutable.Map("a"->1,"b"->2)

    //方式1     + 会生成一个新的集合，原来的集合不变
    //val map2_1: Map[String, Int] = map + "c"->3  //编译不通过
    val map2:  mutable.Map[String, Int] = map + ("c"->3)
    this.print_(map2)  // (a,1) (b,2) (c,3)
    this.print_(map)  // (a,1) (b,2)

    //方式2  + 会生成一个新的集合，原来的集合不变
    val map3:  mutable.Map[String, Int] = map + (("c",3))
    this.print_(map3)  // (a,1) (b,2) (c,3)
    this.print_(map)  // (a,1) (b,2)

    //方式3   添加元素到集合中
    map.put("c",3)
    this.print_(map)  //(b,2) (a,1) (c,3)

    //方式4
    map +=("d"->4)
    this.print_(map)  // (b,2) (d,4) (a,1) (c,3)
  }
  //TODO 2.  遍历方式
  def query(): Unit ={
    println("=========TODO 2.  遍历方式===============")
    val map = mutable.Map("a"->1,"b"->2)

    //方式1  获取某一个元素
    val optA: Option[Int] = map.get("a")
    val optC: Option[Int] = map.get("c")
    println("optA = " + optA)  // optA = Some(1)
    println("optC = " + optC)  // optC = None

    val valueA: Int = optA.getOrElse(-1)
    val valueC: Int = optC.getOrElse(-1)
    println(s"valueA = $valueA")  // valueA = 1
    println(s"valueC = $valueC")  // valueC = -1

    //方式2
    println(map.mkString("\t"))  //b -> 2	a -> 1

    //方式3
    for (elem <- map) {
      print(s"$elem ")
    };println;  // (b,2) (a,1)
    for ((k, v) <- map){
      print(s"[k:${k},v:${v}]")
    };println;

    //通过元组
    map.foreach(tuple=>print(s"[k:${tuple._1},v:${tuple._2}]"));println;//[k:b,v:2][k:a,v:1]

    //方式4 模式匹配
    map.foreach{ case (k,v)=>{
      print(s"[k:${k},v:${v}]")
    }};println;  //[k:b,v:2][k:a,v:1]

//    map.foreach(tuple=>{
//           case (key,value) => print(s"(key:$key,value:$value)");
//    })
  }
  //TODO 1.  声明方式
  def define(): Unit ={
    println("==========TODO 1.  声明方式==============")
    //方式1
    val map: mutable.Map[String, Int] = mutable.Map(("a",1),("b",2))
    this.print_(map)  //(a,1) (b,2)

    //方式2
    val map2 = mutable.Map("a"->1,"b"->2)
    this.print_(map2) // (a,1) (b,2)

  }
  def print_[K,V](map:mutable.Map[K,V]): Unit ={
   // println(map.mkString("\t"))
    for (elem <- map) {
      print(s"$elem ")
    };println;
  }
}
