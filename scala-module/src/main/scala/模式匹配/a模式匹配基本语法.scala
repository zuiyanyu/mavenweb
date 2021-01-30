package 模式匹配

/**
  * 1. 类似java 中的switch语法。 但是更强大。
  * 2. 采用match关键字声明，每个分支采用 case关键字声明。
  * 3. 匹配时候从第一个分支匹配，匹配上后，下面的分支就不会再进行匹配了。
  * 4. 如果所有的分支都没有匹配上，那么就会执行 case _ 分支，类似java 中的default语法。
  * 5. case _ 可以放到任意分支的位置，但总是最后什么都不满足的时候才会被执行。
  * 6. 所有的case分支都不匹配，又没有写case _ 分支，那么会报MatchError
  *
  *
  */
object a模式匹配基本语法 {
  def main(args: Array[String]): Unit = {

      val oper = "*"
      val n1 = 20 ;
      val n2 =30 ;

      var result:Any = null ;
      oper match {
          case "+" => result = n1 + n2
          case "-" => result = n1 - n2
          case "*" => result = n1 * n2
          case "/" => result = n1 / n2
          case "%" => result = n1 % n2
          case _   => result =""
      }
      println(result)



  }

}
