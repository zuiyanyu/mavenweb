package scala集合.Set集合

object Set不可变 {
  def main(args: Array[String]): Unit = {
       //TODO Set集合，存放不重复数据 ，且存储无序
    define
    function
    add
    query
  }
  //TODO 4.  功能介绍
  def function(): Unit ={
    println("=========TODO 4.  功能介绍===============")
    val set: Set[Int] = Set(1,2,3,3,4,5,6,6)

    // 删除数据  产生新集合
    val set2: Set[Int] = set - 1
    this.print_(set2)  // 5	6	2	3	4

    //判断某个元素是否在set 集合中
    val bool: Boolean = set(2)
    println(bool)


  }

  //TODO 3. 元素添加
  def add(): Unit ={
    println("==========TODO 3. 元素添加==============")
    val set: Set[Int] = Set(1,2,3,3,4,5,6,6)

    //方式1 添加一个元素   产生一个新Set
    val set2: Set[Int] = set + 22
    this.print_(set2)  //true



  }
  //TODO 2.  遍历方式
  def query(): Unit ={
    println("=========TODO 2.  遍历方式===============")
    val set: Set[Int] = Set(1,2,3,3,4,5,6,6)

    //方式1
    for (elem <- set) {
      print(elem+"\t")
    };println ;

    //方式2
    println(set.mkString("\t"))

  }
  //TODO 1.  声明方式
  def define(): Unit ={
    println("==========TODO 1.  声明方式==============")

    //方式1   默认声明的是不可变集合
    val set: Set[Int] = Set(1,2,3,3,4,5,6,6)
    this.print_(set)  // 1	2	3

    //方式2
  }
  def print_[T](p:Set[T] ): Unit ={
      println(p.mkString("\t"))
  }
}
