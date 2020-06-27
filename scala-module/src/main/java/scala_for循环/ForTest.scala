package scala_for循环

object ForTest {


  def test1: Unit ={
    var i =0 ;
    for(i <- 1 to  10){
       print("\t" + i);
    }
    println ;
  }

  def test2: Unit ={
    //i 可以不声明
    for(i <- 1 to  10){
      print("\t" + i);
    }
    println ;
  }

  //TODO 使用 i until j 语法(不包含 j)的实例:
  def test3 : Unit ={
    //i 可以不声明
    for(i <- 1 until  10){
      print("\t" + i);
    }
    println ;
  }

  //TODO 在 for 循环 中你可以使用分号 (;) 来设置多个区间，它将迭代给定区间所有的可能值。
  def test4 : Unit ={
    //i 可以不声明  打印 九九乘法表
     for(row <- 1 to  9;col <- 1 to row){
        if(col< row){ print(col + " x " + row + " = " + col*row + "\t");} else  { println;println;}

      }
  }
  // TODO for 循环集合   , List 变量是一个集合，for 循环会迭代所有集合的元素。
  def test5()={
      val list = 1 to 10 ;
      println(list);
      for( i <- list ){
        print(i +"\t");
      }
  }
  //TODO for 循环过滤   循环守卫
  def test6(): Unit ={
      var i = 0 ;
      val list =1 to 10;
      for(i <- list if i <= 3){
         print(i + "\t");
      }
  }

  //TODO for 使用 yield    你可以将 for 循环的返回值作为一个变量存储
  def test7(): Unit ={
    var  list = for{   i <- 1 to 10  if i < 3 }yield i ;
    println(list);
  }

  def main(args: Array[String]): Unit = {
    //    to1;
    //    to2 ;
    //    untilTest ;
    test7();
  }
}


