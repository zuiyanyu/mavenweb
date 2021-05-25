package sparkcore.RDD缓存

import sparkcore.SC.SC

/**
  * 为当前RDD设置检查点。该函数将会创建一个二进制的文件，并存储到checkpoint目录中，
  * 该目录是用SparkContext.setCheckpointDir()设置的。在checkpoint的过程中，
  * 该RDD的所有依赖于父RDD中的信息将全部被移除。对RDD进行checkpoint操作并不会马上被执行，必须执行Action操作才能触发。
  */
object CheckPoint_02 extends SC{
    def main(args: Array[String]): Unit = {
        //TODO （1）设置检查点
        //sc.setCheckpointDir("hdfs://hadoop102:9000/checkpoint")
        sc.setCheckpointDir("D:\\checkpoint")

        //（2）创建一个RDD
        val rdd = sc.parallelize(Array("spark"))

        //（3）将RDD转换为携带当前时间戳
        val ch = rdd.map(_+System.currentTimeMillis)

        // (4) TODO 做checkpoint
        ch.checkpoint()

        //（5）多次打印结果
        println(ch.collect.mkString(","))
        println(ch.collect.mkString(","))
        println(ch.collect.mkString(","))

        /** 输出结果：
          * spark1613216820166
          *
          * spark1613216820340
          * spark1613216820340
          */

    }
}
