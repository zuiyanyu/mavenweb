package scala类和对象04

object 枚举类 {
    def main(args: Array[String]): Unit = {
        val color = Color.BLUE;
        println(color)
    }

}
object Color extends Enumeration{
    val RED = Value(0,"RED")
    val BLUE = Value(1,"BLUE")
}
