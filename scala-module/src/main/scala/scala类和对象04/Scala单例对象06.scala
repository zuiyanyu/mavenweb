package scala类和对象04


/**
  * Scala 单例对象
  * 1.在 Scala 中，是没有 static 这个东西的，但是它也为我们提供了单例模式的实现方法，那就是使用关键字 object。
  *
  * 2.Scala 中使用单例模式时，除了定义的类之外，还要定义一个同名的 object 对象，它和类的区别是，object对象不能带参数。
  *
  * 3.当单例对象与某个类共享同一个名称时，他被称作是这个类的伴生对象：companion object。
  * 4.你必须在同一个源文件里定义类和它的伴生对象。类被称为是这个单例对象的伴生类：companion class。
  * 5.TODO 类和它的伴生对象可以互相访问其私有成员。
  */
object Scala单例对象 {
    def main(args: Array[String]) {
        //val marker22 = new Marker("gggggg")  //报异常
       //TODO 方式1：获取单例对象
        val marker = Marker.getMarker("red")
        val color = marker.color
        println(color);  //获取的是构造方法中的参数值

        //TODO 方式2：获取单例对象
        val marker2 = Marker("red")
        println(marker2)

    }
}

// TODO 私有构造方法
// TODO 构造器使用 private 进行修饰  : private (val xc: Int, val yc: Int)
class Marker private(val color:String) {
  //构造方法体  会调用 toString方法
  println("创建" + this)

  override def toString(): String = { "颜色标记=====："+ color }
}

// TODO 伴生对象，与类名字相同，可以访问类的私有属性和方法
object Marker{
      //TODO 伴生对象体内可以进行创私有对象的实例。
      //伴生对象中创建一些实例对象
      private val markers: Map[String, Marker] = Map(
          "red"   -> new Marker("red"   ),
          "blue"  -> new Marker("blue"  )
      )

      // val marker = Marker("red") 调用会走这里
      def apply(color:String) = {
        if(markers.contains(color)) markers(color) else null
      }

      //val marker = Marker.getMarker("blue")
      def getMarker(color:String) = {
        if(markers.contains(color)) markers(color) else null
      }

}
