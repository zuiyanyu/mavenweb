package Scala方法与函数

object MethodTest {
  def main(args: Array[String]): Unit = {
     forOne ;
    forTwo ;
    forTwo();
  }

  //TODO 调用方式只能为 forOne 不能为 forOne();
  def forOne: Unit ={
    var i =0 ;
    for(i <- 1 to  10){
      print("\t" + i);
    }
    println ;
  }

  //TODO 既可以使用 forTwo()调用；  也可以使用forTwo 调用；
  def forTwo(): Unit ={
    var i =0 ;
    for(i <- 1 to  10){
      print("\t" + i);
    }
    println ;
  }
}
