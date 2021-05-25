package sparkcore.累加器

import sparkcore.SC.SC
import org.apache.spark.util.AccumulatorV2

/**
  *
  */
object 系统累加器 extends SC{
    //针对一个输入的日志文件，如果我们想计算文件中所有空行的数量，我们可以编写以下程序：
    def main(args: Array[String]): Unit = {
        type_01
    }

    //方式1： 过时的方式  从sc中获取一个累加器使用 ,目前的方式是自定义一个累加器，然后注册到SC中
    def type_01(): Unit ={
        val notice = sc.textFile("D:\\hadoop\\accumulator")

        //TODO 获取一个累加器 （2.0版本，accumulator已经过时，使用AccumulatorV2替换）
        val blanklines = sc.accumulator(0)

        //TODO 转换算子中使用累加器  查找累加空行的数量
        val tmp = notice.flatMap(line => {
            if (line == "") {
                blanklines += 1
                //blanklines.add(1); //二选一
            }
            line.split(",")
        })
        // 数据总量
        val total: Long = tmp.count()
        println("total = " + total) //total = 46

        //TODO 输出累加器的值
        val value: Int = blanklines.value
        println("value = " + value)  //value = 6

    }
}
