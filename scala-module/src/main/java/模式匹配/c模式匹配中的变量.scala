package 模式匹配

object c模式匹配中的变量 {
    def main(args: Array[String]): Unit = {
        //TODO 如果在case 关键字后面跟上变量名， 那么match 前，表达式的值会赋值给那个变量。

        val ch = 'V'
        ch match {
            case '+' => println("+")
            case chtmp => println(s"chtmap = $chtmp")
            case _ => println("无匹配")
        }
    }

}
