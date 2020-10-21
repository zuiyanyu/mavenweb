package scala集合.Map集合

import scala.collection.mutable

object Map应用实例 {
  def main(args: Array[String]): Unit = {
    aplication_1
  }

  def aplication_1(): Unit ={
    //将 Map("a"->1,"b"->2,"c"->3) 使用 foldLeft 合并到 Map("a"->3,"c"->2, "d"->1)
    //结果为 Map("a"->4,"b"->2,"c"->5,"d"->1)

    val map1 = mutable.Map("a"->1,"b"->2,"c"->3)
    val map2 = mutable.Map("a"->3,"c"->2, "d"->1)

    //将map1 的元素 逐个和map2做运算
    map1.foldLeft(map2){case (map2,(k,v))=>{
      val initValue: Int = map2.getOrElse(k,0)
      map2(k)=initValue+v    //map2.put(k,initValue+v)
      map2
    }}
    println(map2)

  }
}
