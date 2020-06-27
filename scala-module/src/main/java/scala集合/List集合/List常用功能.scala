package scala集合.List集合

import scala.collection.GenTraversableOnce
import scala.collection.mutable.ListBuffer

object List常用功能 {
  def main(args: Array[String]): Unit = {
    val list: ListBuffer[Int] = ListBuffer(4,5,6,17,12,11)
    this.println_(list)

    //list 反转，产生新的集合
    val reverse: ListBuffer[Int] = list.reverse
    this.println_(reverse)  //4	3	2	1

    val max: Int = list.max
    println(s"max=$max")

    val min: Int = list.min ;
    println(s"min=$min")

    val sum: Int = list.sum
    println(s"sum=$sum") //sum=55

    val sum2: Int = list.sum( Numeric.IntIsIntegral)
    println(s"sum2=$sum2")//sum=55

//    val sortBy: (Int => Any) => ListBuffer[Int] = list.sortBy _
    val sorted: ListBuffer[Int] = list.sorted
    println(sorted)//ListBuffer(4, 5, 6, 11, 12, 17)


    val list2: ListBuffer[Int] = ListBuffer(4,5,6,16,14,13)

    //TODO 自定义规则进行排序所需要的key
    //TODO (默认按照字典顺序比较、或者数值大小比较结果，来确定排序的方式:升序或者降序)
    val sortBy: ListBuffer[Int] = list2.sortBy((elem) => {
      val i: Int = elem % list2.size
       i
    })
    println(sortBy) //ListBuffer(6, 13, 14, 4, 16, 5)

    //TODO 自定义两个key值比较方式，比较结果来决定排序的方式： 升序 或者 降序 。
    val ints: ListBuffer[Int] = list2.sortWith((left, right) => {
      //左边 大于 右边 ，是降序排序
      left > right   //if(left > right) true; else  false ;
    })
    println(ints); //ListBuffer(16, 14, 13, 6, 5, 4)

    //TODO 对元素进行分组  （比如按照元素的奇偶进行分组）
     val groupBy: Map[String, ListBuffer[Int]] = list2.groupBy(x=>{if(x%2==1) "奇数" else "偶数"})
    println(groupBy) // Map(奇数 -> ListBuffer(5, 13), 偶数 -> ListBuffer(4, 6, 16, 14))

    //TODO map()将集合中的每一个方法进行转换后放到新的集合中
    val listMap: ListBuffer[(Int, Int)] = list2.map((_,1))
    this.println_(listMap)  // (4,1)	(5,1)	(6,1)	(16,1)	(14,1)	(13,1)

    //TODO 扁平化 ：将集合中的每个元素的子元素映射到某个函数并返回新的集合。
    //TODO flat即压扁，扁平化；
    val list5 =List(List(1,2,3,4),List(5,6,7,8))
//    println(222.isInstanceOf[GenTraversableOnce[Any]])
//    println(list5.isInstanceOf[GenTraversableOnce[Any]])
  //TODO  输入 in： List   ， 输出 out：Iterator  不管你输入的是什么，但是输出的必须是可迭代的类型即可。
    println(list5.flatMap( x => x))  //List(1, 2, 3, 4, 5, 6, 7, 8)


    //reduce
    val list6: ListBuffer[Int] = ListBuffer(1,2,3,4)
    //TODO 折叠/聚合函数：从左往右，相邻的两个数据做聚合  入参：op: (A1, A1) => A1
    val reduceRest: Int = list6.reduce((l, r) => l + r)  // list6.reduce( _+_)
    println(reduceRest);  // 10

    //TODO 折叠/聚合函数： 和 reduce 的作用一样 reduce 底层就是调用reduceLeft
    println(list6.reduceLeft(_+_))  //10
    println(list6.reduceLeft(_-_)) //-8


    //TODO 折叠/聚合函数：从右往左，相邻的两个数据做聚合
    println(list6.reduceRight(_+_))//10
    println(list6.reduceRight(_-_))//-2  = (1-(2-(3-4)) )

    //TODO 折叠/聚合函数：从左往右，初始化的元素和未计算过的相邻元素 (两者类型必须相同，且计算结果也必须相同)做运算进行聚合。
    // def fold[A1 >: A](z: A1)(op: (A1, A1) => A1): A1 = foldLeft(z)(op)
    println(list6.fold(0)(_ + _)) // 10
    println(list6.fold(1)(_ + _)) // 11

    //TODO 折叠/聚合函数：从左往右，初始化的元素和未计算过的相邻元素 (两者类型可相同，但是计算结果必须和初始化值类型相同)做运算进行聚合。
    //def foldLeft[B](z: B)(op: (B, A) => B): B = underlying.foldLeft(z)(op)
    println(list6.foldLeft(0)(_ + _)) //   （（（（0+ 1）+ 2）+3+4）=10
    println(list6.foldLeft(0)(_ - _))  //  （（（（0- 1）- 2）-3-4） = -10

    println(list6.foldRight(0)(_ - _))//     (1-(2-(3-(4-0)))) = -2

    //过滤器
  }
  def println_[T](buffer:ListBuffer[T]){
    println(buffer.mkString("\t"))
  }
}
