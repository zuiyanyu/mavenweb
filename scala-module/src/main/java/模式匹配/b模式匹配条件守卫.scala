package 模式匹配

object b模式匹配条件守卫 {
    def main(args: Array[String]): Unit = {
        //TODO 如果想要表达匹配某个范围的数据，就需要在模式匹配中增加条件守卫

        for(ch <- "+-*/34!"){

            var sign = 0 ;
            var digit = 0 ;

            ch match{
                case '+' => sign = 1
                case '-' => sign =2
                case _   if ch.toString.equals("3")
                          =>   digit =3 ;println("==========")
                case _    => sign = -1
            }
            println(s"ch => $ch ; sign => $sign; digit=> $digit")
        }

    }

}
