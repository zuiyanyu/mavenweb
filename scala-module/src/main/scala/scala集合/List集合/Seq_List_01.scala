package scala集合.List集合

/**
  * scala 集合 之list
  */
object Seq_List_01 {
    def main(args: Array[String]): Unit = {
        listDefine
        listFunction
    }

    //TODO 4. 不可变list的功能介绍
    def listFunction(): Unit ={
        println("=========TODO 4. 不可变list的功能介绍============")
        val list = List(1,2,3,4);

        val list2 = list :+ 5
        this.printList(list) ;
        this.printList(list2) ;

        val list3 =5 +: list
        this.printList(list) ;
        this.printList(list3) ;

        //特殊 list 对象 Nil ，表示空集合 List()  即 Nil => List()
        // Nil.type = List[Nothing]    Nil = List()   case object Nil extends List[Nothing]
        val list4: Nil.type = Nil
        val list5: List[Nothing] = Nil
        val list6: List[Nothing] = List() ;

        //使用 :+ 为Nil对象添加元素，返回一个新的对象
//        val nil4: List[Int] = Nil
        val list7 = Nil :+5
        printList(list7)

        //使用 :: 为Nil对象添加元素，返回一个新的对象   运算顺序是从右往左
        val list8:List[Int] = 5 :: 4 :: 3 :: 2 ::  Nil
        printList(list8)// 5 	4	3	2

        val list9: List[Any] = 5::list::Nil
        printList(list9) //5	List(1, 2, 3, 4)

        // ::: 扁平化操作。  将一个整体拆分成一个一个的个体。
        // 将list集合中元素逐个添加到 Nil当中，而不是将list整体当成一个元素放到Nil中
         val list10: List[Int] = 5::list:::Nil  //5	1	2	3	4
        printList(list10)

        //修改数据元素 ,但是产生新的集合
        val list11: List[Int] = list10.updated(0, 19)
        printList(list11)  //19	1	2	3	4

        //删除一个数据元素，但是参数是表示删除数据的个数(从前往后删除)
        val list12: List[Int] = list11.drop(2)
        printList(list12)   //2 	3	4
        printList(list11)  //19	 1	2	3	4

        //获取数据
        println(list11(2))  // 2

        //获取集合中前两个元素
        val list13: List[Int] = list11.take(2)
        printList(list13)  // 19	1

    }
    //TODO 3.不可变list的元素添加方式
    def listAddElement(): Unit ={
        println("========TODO 3.不可变list的元素添加方式=============")

        val list = List(1,2,3,4);
    }
    //TODO 2. 不可变list的遍历方式
    def listGetElement(): Unit ={
        println("==========TODO 2. 不可变list的遍历方式===========")
        val list = List(1,2,3,4);

        //方式1
        for (elem <- list) {
            print(elem +"\t")
        };println;

        //方式2
        println(list.mkString("\t"))

        //方式3
        list.foreach(println)

    }
    //TODO 1. 不可变list的声明方式
    def listDefine(): Unit ={
        println("=========TODO 1. 不可变list的声明方式============")

        val list = List(1,2,3,4,"a","b","c");

        this.printList(list)
    }
    def printList[T](list:List[T]): Unit ={
        for (elem <- list) {
            print(elem +"\t")
        };println;
    }
}
