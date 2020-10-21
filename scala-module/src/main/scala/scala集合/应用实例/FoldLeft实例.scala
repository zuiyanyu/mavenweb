package scala集合.应用实例

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object FoldLeft实例 {
  def main(args: Array[String]): Unit = {
    exam01
    exam02
  }

  /**
    * val sentence = "AAAAAABBBCCCCDDD"
    * 将sentence中各个字符，通过foldLeft放到一个ArrayBuffer中
    */
  def exam01(): Unit = {
    val sentence = "AAAAAABBBCCCCDDD"
    val buffer: ArrayBuffer[Char] = new ArrayBuffer[Char]()
    val stringToBufferChar: ArrayBuffer[Char] = sentence.foldLeft(buffer)((buffer, char) => {
      buffer.append(char);
      buffer
    })
    println(stringToBufferChar) // ArrayBuffer(A, A, A, A, A, A, B, B, B, C, C, C, C, D, D, D)
  }

  /**
    * val sentence = "AAAAAABBBCCCCDDD"
    * 通过foldLeft 统计sentence中各个字母出现的次数
    */
  def exam02(): Unit = {
    val sentence = "AAAAAABBBCCCCDDD"

    //开始进行单词统计
    val map = mutable.Map[Char, Int]()
    sentence.foldLeft(map)((resMap, char) => {
      map(char) = map.getOrElse(char, 0) + 1
      map
    })

    //根据单词数量进行降序排序
    map
      .toList  //转换成list集合
      .sortWith((left, right) => {    // 根据单词数量降序排序
        left._2 > right._2
      })
      .foreach(println)  //打印结果

    //按照字母顺序对统计结果进行输出
    map
      .toList //转换成list集合
      .sortBy { case (char, count) => char}
      .foreach(println)

  }

}
