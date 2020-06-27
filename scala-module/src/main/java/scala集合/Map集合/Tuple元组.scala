package scala集合.Map集合

object Tuple元组 {
  def main(args: Array[String]): Unit = {
    //TODO Tuple 元组：将多个无关联的数据当成一个整体使用 （一个元组最多只能有22个元素）
    //TODO 当tuple中的元素只有两个的时候，我们称之为 :"对偶" ,Map集合汇总的key-value 就是对偶 ，即（keyy,value）

    //TODO 声明元组： 使用小括号
    val tuple: (String, Int, Double, Boolean) = ("abc",234,2.3,true)

    //TODO 访问元组
    //使用特殊方式( .+下划线+顺序号)访问元组数据
    val value2: String = tuple._1
    println("value2 = "+value2)

    //使用迭代器
    val tupleIterator: Iterator[Any] = tuple.productIterator
    for (elem <- tupleIterator) {
      print(s"$elem\t")
    };println; //abc	234	2.3	true

    val tupleIterator2: Iterator[Any] = tuple.productIterator
    while(tupleIterator2.hasNext){
      val elem: Any = tupleIterator2.next()
      print(s"$elem\t")
    };println;  //abc	234	2.3	true


  }

}
