package scala类和对象04

object scala类继承 {
  def main(args: Array[String]): Unit = {
    //    Apple("苹果","郑州");
    val fruit = new Fruit("水果")
    val str = fruit.getUserInfo("？")
    println(str);

    val apple = new Apple("苹果","郑州")
    val s = apple.getUserInfo("红色")
    println(s);

  }
}

// name 默认是 val 变量  可以改成变量
//class Fruit(  name : String ){
class Fruit(  name : String ){
  val _name = name ;

  def getUserInfo(color : String ): String ={
      "Fruit " + _name + " 的颜色 是 " + color
  }
}
//报错 1. 不能重写var 变量  2. 构造方法中，重写的变量前不能加 override
class Apple(val name:String ,fromAddress : String ) extends Fruit(name : String){
  override val _name = name  ;
  val _fromAddress = fromAddress ;

  override def getUserInfo(color : String ): String ={
    "Apple " + _name + " 的颜色是 " + color +" 产地为 " + _fromAddress
  }

}



//  报错


//class Point(xc: Int, yc: Int) {
//  var x: Int = xc
//  var y: Int = yc
//
//  def move(dx: Int, dy: Int) {
//    x = x + dx
//    y = y + dy
//    println ("x 的坐标点: " + x);
//    println ("y 的坐标点: " + y);
//  }
//}
//
//class Location(override val xc: Int, override val yc: Int, val zc :Int) extends Point(xc, yc){
//  var z: Int = zc
//
//  def move(dx: Int, dy: Int, dz: Int) {
//    x = x + dx
//    y = y + dy
//    z = z + dz
//    println ("x 的坐标点 : " + x);
//    println ("y 的坐标点 : " + y);
//    println ("z 的坐标点 : " + z);
//  }
//}
