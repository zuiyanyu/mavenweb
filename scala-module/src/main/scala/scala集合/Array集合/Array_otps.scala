package scala集合.Array集合

object Array_otps {
    def main(args: Array[String]): Unit = {
        array_headOption
    }
    def array_headOption(): Unit ={
        val array = Array(1,2,3)
        //获取数组元素的第一个值   Optionally selects the first element.
        val array_option: Option[Int] = array.headOption
        val ret: Int = array_option.getOrElse(20)
        print(ret)
    }
}
