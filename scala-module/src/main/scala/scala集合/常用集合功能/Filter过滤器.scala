package scala集合.常用集合功能

object Filter过滤器 {
  def main(args: Array[String]): Unit = {
    //TODO filter 将符合要求的数据(筛选)放置到新的集合中
    val worlds = List("hello","world","hahaha")

    val filterRes: List[String] = worlds.filter(x => {
      if (x.startsWith("h")) true else false
    })
    println(filterRes)  // List(hello, hahaha)

  }

}
