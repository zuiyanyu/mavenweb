package scala集合.Array集合

/**
  * 数组
  * //java :数组 ==> scala: Array
  * //java :List ==> scala:Seq
  * //java :Set  ==> scala:Set
  * //java :Map  ==> scala:Map
  */
object Array一维数组 {
    /**
      * 不可变数组的测试
      * @param args
      */
    def main(args: Array[String]): Unit = {

        arrayFunction

    }
    //TODO 4.数组的功能方法介绍
    def arrayFunction(): Unit ={
        val array = Array("d");
        val array2 = Array("a","b","c")
        val array3 = new Array[String](array.length + array2.length)

        println("========================")
        //TODO 功能1：  Array.copy  将一个数组中的元素拷贝到另一个数组中
         //val arrayCopy = Array.copy(array, 0, array2, array2.length, array.length) // java.lang.ArrayIndexOutOfBoundsException
        //copy(src: AnyRef, srcPos: Int, dest: AnyRef, destPos: Int, length: Int)  将src的数据拷贝到 dest数组中。
        Array.copy(array, 0, array2, array2.length-1, array.length)
        println(array2.mkString(":")); // a:b:d

        //将数组array 和 数组 array2的元素拷贝到array3中
        Array.copy(array, 0, array3,0, array.length)  //先将array中的元素拷贝到array3中
        Array.copy(array2, 0, array3,array.length, array2.length)  //再讲array2中的元素拷贝到array3中
        println(array3.mkString(":")); // a:b:d

        println("========================")
        //TODO 功能2  :+  添加元素到集合尾部，生成一个新的Array
        val newArray1 = array.:+("李四")
        //简化
        val newArray1_1 = array :+ "李四"

        println(newArray1.mkString(":"))
        println(newArray1_1.mkString(":"))

        println("========================")
        //TODO 功能3  +: 添加元素到集合头部，生成一个新的集合
        val newArray2 = "李四" +: array ;
        println(newArray2.mkString(":"))

        //if(array.contains("张三")) println("存在")
    }
    //TODO 3. 访问 不可变Array集合方法数组元素
    def arrayGetElement()={
        val array = new Array[String](3);
        array(0) = "张三"
        array(1) = "李四"
        array(2) = "王五"

        //方式1 单个元素访问
        println("方式1 单个元素访问")
        print(array(0) +"\t" +array(1) +"\t" +array(2) +"\t\n")

        //方式2 增强for循环
        println("方式2 增强for循环")
        for(x<- array){
            print(x+"\t")
        };println;
        //增强for循环简化版
        array.foreach(x => print(x + "\t"));println;

        //方式3 基本for循环 + 下标索引
        println("方式3 基本for循环 + 下标索引")
        for(i<- 0 until array.length){
            print(array(i)+"\t")
        };println ;

        //方式4 array提供的mkString()方法
        println("方式4 array提供的mkString()方法")
        val str = array.mkString("[", ":", "]")
        println(str);// [张三:李四:王五]
        println( array.mkString(":")) // 张三:李四:王五

    }
    // TODO 2. 不可变Array集合添加元素  scala 中的集合默认是不可改变的，添加元素后会返回一个新的集合
    def arrayAddElement(): Unit ={
        //声明一个空数组
        val array = Array(1,2,3);
        //方式1   给array添加一个元素，返回一个新的集合
        val newArray = array :+ 5

        array.foreach(x => print(x + "\t"));println;
        newArray.foreach(x => print(x + "\t"));println;

    }
    //TODO 1. 声明数组   默认情况下，scala中集合的声明全都是不可变的。
    def arrayDefine(): Unit ={

        //TODO 声明数组方式1
        println("声明数组方式1")
        val array1 = new Array[Int](3)
        val array1_1 = new Array[Int](3):Array[Int]
        array1(0) = 10 ;
        array1(1) = 11 ;
        array1(2) = 12 ;
        array1.foreach(x => print(x+"\t"));println;

        //TODO 方式2 声明数组
        println("声明数组方式2")
        val array2:Array[Int] = Array(1,2,3,4)
        //简化
        val array3 = Array(1,2,3,4)

        array2.foreach(x => print(x+"\t"));println;
        array3.foreach(x => print(x+"\t"));println;

        //TODO 方式3 声明数组
        println("声明数组方式3")
        val array4 = Array.apply(1,2,3,4);
        array4.foreach(x => print(x+"\t"));println;

        //TODO 方式4  Array.ofDim() 生成一维数组
        println("Array.ofDim() 生成一维数组")
        val array5 = Array.ofDim[Int](3)
        array5(0) = 10 ;
        array5(1) = 11 ;
        array5(2) = 12 ;
        array5.foreach(x => print(x+"\t"));println;

    }

}
