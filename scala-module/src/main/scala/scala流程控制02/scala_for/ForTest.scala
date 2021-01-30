package scala流程控制02.scala_for

/**
  * 循环控制
  * TODO : scala中的循环方式和java不太相同,scala没有如下方式的循环
  *
  * Java的循环:
  * for ( int i = 0; i < 10; i+=3 ) {
  * i = 100
  * }
  *
  * for ( int i = 1; i < 3; i++ ) {
  *
  * }
  *
  * for ( Object obj : list ) {
  *
  * }
  *
  *
  */
object ForTest {

    /**
      * Ranger的使用
      * to() 和 until
      */
    def test0(): Unit = {
        // to 是一个方法，是RichInt的非静态方法方法
        val inclusive: Range.Inclusive = 1 to 10

        val range1: Range = 1 until 10

        //  def apply(start: Int, end: Int, step: Int): Range = new Range(start, end, step)
        val range2 = Range(1,10,1)  //调用Range对象伴生类中的 apply方法，apply方法会实例化一个Range对象返回
        val range3 = Range.apply(1,10,1)  //调用Range对象伴生类中的 apply方法，apply方法会实例化一个Range对象返回
        val range4 = new Range(1,10,1) //直接实例化一个Range 对象

        for (i <- range4) {
            print("\t" + i);
        }
    }

    /**
      * to ：[start, stop]左右闭区间
      */
    def test1: Unit = {

        val j = 0;
        //for外面虽然定义了i 为val, 但是进入for里边,就不再使用了，重新定义为 val
        for (j <- 1 to 10) {
            print("\t" + j);
        }
        print("j = "+j) // j = 0
        println;


        var i = 0;
        for (i <- 1 to 10) {
            print("\t" + i);
            //外层虽然定义了i 为var, 但是进入里边仍然是val类型
            //i = 9;
        }
        print("i= " + i )  //j = 0
        println;

    }

    def test2: Unit = {
        //i 可以不声明
        for (i <- 1 to 10) {
            print("\t" + i);
        }
        println;
    }


    //TODO 使用 i until j 语法(不包含 j)的实例: [start, stop )  左闭右开
    def test3: Unit = {
        //i 可以不声明
        for (i <- 1 until 10) {
            print("\t" + i);
        }
        println;
    }

    //TODO 双重循环： 在 for 循环 中你可以使用分号 (;) 来设置多个区间，它将迭代给定区间所有的可能值。
    def test4: Unit = {
        //i 可以不声明  打印 九九乘法表
        for (row <- 1 to 9; col <- 1 to row) {

            // 行固定，打印列
            if (col < row) {
                print(col + " x " + row + " = " + col * row + "\t");
            } else {
                println;
                println;
            }

        }
    }

    /**
      * to 是一个函数，返回结果是一个list集合
      * TODO for 循环集合   , List 变量是一个集合，for 循环会迭代所有集合的元素。
      */
    def test5() = {
        val list = 1 to 10;
        println(list);

        for (i <- list) {
            print(i + "\t");
        }
    }

    //TODO for 循环过滤   循环守卫
    def test6(): Unit = {
        var i = 0;
        val list = 1 to 10;
        for (i <- list if i <= 3) {
            print(i + "\t");
        }
    }

    //TODO for 使用 yield    你可以将 for 循环的返回值作为一个变量存储
    def test7(): Unit = {
        var list = for {i <- 1 to 10 if i < 3} yield i;
        println(list);

        var list2 = for (i <- 1 to 10 if i < 3) yield i;
        println(list2);
    }

    def test8() = {
        // TODO scala 可以在一个表达式式中使用多行语句，为了理解方便，将小括号便成花括号。
        //TODO  for循环也有返回值，默认返回值为 Unit 即（）
        // TODO 将遍历过程中处理的结果返回到一个新的Vector集合中，使用yield关键字

        var result = for {
            i <- 1 to 10
            j = i * 2
            k = j + 1
            if (k < 20)
        } yield k
        println(s"result=${result}"); // result=Vector(3, 5, 7, 9, 11, 13, 15, 17, 19)

        //将每一个元素都给yield ，让yield进行处理，然后返回处理结果
        var result2 = for {
            i <- 1 to 10
            j = i * 2
            k = j + 1
        } yield {
            if (k < 20) k
        }
        println(s"result2=${result2}"); //  result2=Vector(3, 5, 7, 9, 11, 13, 15, 17, 19, ())


        println("============");
    }

    def main(args: Array[String]): Unit = {
        //    to1;
        //    to2 ;
        //    untilTest ;
        test1;
    }
}


