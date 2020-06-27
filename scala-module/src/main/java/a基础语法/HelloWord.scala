package a基础语法

/**
  * Scala的命名规范：
  *   1. "$"开头的标识符为保留的 Scala 编译器产生的标志符使用，应用程序应该避免使用"$"开始的标识符，以免造成冲突。
  *   2.  该避免使用以下划线 _ 结尾的标志符以避免冲突.
  *   3. Scala 的命名规则采用和 Java 类似的 camel 命名规则，首字符小写
  *
  * 分割符：
  *   1. Scala 程序里,一行语句末尾的分号通常是可选的，可不写。如果一行里写多个语句那么分号是需要的
  */
object HelloWord {

    def main(args:Array[String]): Unit ={
       println("hello world !");
    }
}


