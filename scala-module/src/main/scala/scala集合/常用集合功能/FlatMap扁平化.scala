package scala集合.常用集合功能

object FlatMap扁平化 {
  def main(args: Array[String]): Unit = {
    //TODO 扁平化 ：将集合中的每个元素的子元素映射到某个函数并返回新的集合。
    //TODO flat即压扁，扁平化；
    val list5 =List(List(1,2,3,4),List(5,6,7,8))
    //    println(222.isInstanceOf[GenTraversableOnce[Any]])
    //    println(list5.isInstanceOf[GenTraversableOnce[Any]])
    //TODO  输入 in： List   ， 输出 out：Iterator  不管你输入的是什么，但是输出的必须是可迭代的类型即可。
    println(list5.flatMap( x => x))  //List(1, 2, 3, 4, 5, 6, 7, 8)
  }
  def option(): Unit ={
    val array = Array(1,3)
    //TODO array 的 flatMap可以应用到Option上
    val flat: Array[String] = array.flatMap(_=>Option("1"))
    val str: String = flat.mkString(",")
  }
}
