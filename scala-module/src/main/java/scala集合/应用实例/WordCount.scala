package scala集合.应用实例

object WordCount {
  def main(args: Array[String]): Unit = {
    //数据
    val dataList = List("hello word","hello zhangsan","ha ha ha")

    //1.将数据拆分成单词  扁平化
    val wordsList: List[String] = dataList.flatMap(line => line.split("\\s+"))
    println(wordsList) // List(hello, word, hello, zhangsan, ha, ha, ha)

    //2.  将相同的单词进行分组
    val wordGroupBy: Map[String, List[String]] = wordsList.groupBy(word => word)
    println(wordGroupBy) // Map(zhangsan -> List(zhangsan), ha -> List(ha, ha, ha), word -> List(word), hello -> List(hello, hello))

    //3. 进行转换为 元组 (单词，数量)
    val wordAndCountMap: Map[String, Int] = wordGroupBy.map{case (key,list)=>{(key,list.size)}}
    println(wordAndCountMap) // Map(zhangsan -> 1, ha -> 3, word -> 1, hello -> 2)

    //4. 转为 List[(word,wordcounts)] ,方便排序
    val wordCountTupleList: List[(String, Int)] = wordAndCountMap.toList

    //5. 进行排序   降序排序
    val sortedByCounts: List[(String, Int)] = wordCountTupleList.sortWith {
      case ((lworld, lcounts), (rworld, rcounts)) => lcounts > rcounts
    }
    println(sortedByCounts) //List((ha,3), (hello,2), (zhangsan,1), (word,1))

     //6 遍历
    for ((word,count) <- sortedByCounts) {
      println(s"$word -> $count");
    }
//==========================
    //方式2
    val wordToList: Map[String, List[Int]] = wordGroupBy.map { case (k, v) => {
      (k, v.map(w => 1))
    }
    }
    val worldToCount: Map[String, Int] = wordToList.map{case (world,list)=>{(world,list.sum)}}

    val resultTuples: List[(String, Int)] = worldToCount.toList.sortWith{(left,right)=>left._2 > right._2}

    resultTuples.foreach(println)
  }

}
