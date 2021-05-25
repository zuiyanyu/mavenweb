package sparkcore.累加器

import java.util

import org.apache.spark.{SparkConf, SparkContext}

/**
  * TODO 使用 sc.accumulator(0) 的方式获取一个累加器已经过时，现在要求是自定义一个累加器，然后注册到sc中
  * TODO 自定义累加器需要 继承AccumulatorV2这个抽象类，并实现其方法。
  *
  * TODO // @deprecated("use AccumulatorV2", "2.0.0")
  * // def accumulator[T](initialValue: T)(implicit param: AccumulatorParam[T]): Accumulator[T]
  */
object 自定义累加器 {
    //TODO   过滤掉带字母的
    def main(args: Array[String]): Unit = {
        val conf = new SparkConf().setMaster("local[2]").setAppName("LogAccumulator")
        val sc = new SparkContext(conf)

        //TODO 创建并注册累加器
        val accum = new CustomAccumulator[String]
        sc.register(accum, "logAccum")

        val sum = sc.parallelize(Array("1", "2a", "3"), 2).filter(
            line => {
                //匹配数字
                val pattern = """^-?(\d+)"""
                val flag = line.matches(pattern)

                //如果没匹配上，就是带字幕的
                if (!flag) {
                    accum.add(line)
                }
                flag
            }).map(_.toInt).reduce(_ + _)

        println("sum: " + sum)
        val value2: util.Set[String] = accum.value

        //遍历
        val value: util.Iterator[String] = value2.iterator()
        val str: String = value.next()
        println(str)

        //下方的遍历都会报错
        //value2.forEach(println(_))
        //value.forEach(println)

//        value.forEach ((v)  => {print(v + "")})
//        for(v:String <- value){
//            print(v + "")
//        }


        println()
        sc.stop()
    }

}
