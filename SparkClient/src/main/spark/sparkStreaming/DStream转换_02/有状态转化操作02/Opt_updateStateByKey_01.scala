package sparkStreaming.DStream转换_02.有状态转化操作02

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Opt_updateStateByKey_01 {
    def main(args: Array[String]): Unit = {
        //1.初始化spark的配置信息
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("DStream")

        //2.初始化sparkStreamingContext
        val streamingContext = new StreamingContext(sparkConf, Seconds(5))

        //TODO 1. 设置checkPoint的路径，保存状态值
        streamingContext.sparkContext.setCheckpointDir("state")

        //3.通过监控端口创建DStream，读进来的数据为一行一行的
        //即 监控端口实时读取数据 按行读取
        val socketDStream: ReceiverInputDStream[String] =
        streamingContext.socketTextStream("hadoop102", 1234)


        //4.将每一行数据进行切分，形成一个一个的单词 .按空格切分
        val wordsStream: DStream[String] = socketDStream.flatMap(line=>{line.split(" ")})
        //5.将单词映射成元组(word,1)
        val wordAndOneStream: DStream[(String, Int)] = wordsStream.map{(_,1)}

        //TODO 进行有状态的转换操作
        /* TODO 源码：
     //updateFunc：自定义状态更新函数。入参 Option[S] 防止传递进来的参数为空指针。
        def updateStateByKey[S: ClassTag](
                    updateFunc: (Seq[V], Option[S]) => Option[S]
                  ): DStream[(K, S)] = ssc.withScope {
           updateStateByKey(updateFunc, defaultPartitioner())
         }
         //TODO 核心代码
         def updateStateByKey[S: ClassTag](
              updateFunc: (Seq[V], Option[S]) => Option[S],
              partitioner: Partitioner
         ): DStream[(K, S)] = ssc.withScope {
            val cleanedUpdateF = sparkContext.clean(updateFunc)
            //TODO 核心计算逻辑  看到每个批次的数据 和  Option[S] 传递个了 cleanedUpdateF进行处理，
            //TODO 然后再进行(key，处理结果)的 元组形式进行结果保存
            val newUpdateFunc = (iterator: Iterator[(K, Seq[V], Option[S])]) => {
              iterator.flatMap(t => cleanedUpdateF(t._2, t._3).map(s => (t._1, s)))
            }
            updateStateByKey(newUpdateFunc, partitioner, true)
          }
         */
        //根据key 对checkPoint中的处理结果进行更新。
        val resultDStream: DStream[(String, Long)] = wordAndOneStream.updateStateByKey {
            //(key,seq):(key,ListSeq(1,1,1,1))  聚合 （key,opt(要更新的状态数据)）
            //将具有相同的key 的values:即seq 和opt传进来，进行相关操作
            //seq : values集合 比如 ListSeq(1,1,1,1)
            //传递进来的是Option(checkPoint中保存的处理结果)，防止空指针，第一次更新状态，文件中没处理结果，为null 。
            case (seq, opt) => {
                val sumCount = seq.sum + opt.getOrElse(0L)
                Option(sumCount) //将新的处理结果保返回
                /* TODO 源码：
                def apply[A](x: A): Option[A] = if (x == null) None else Some(x)
                作用：
                Option(sumCount) 是对sumCount进行空指针检查判断，不为空则返回Some(sumCount),
                为空返回None
                */
            }
        }
        resultDStream.print()

        //将相同的单词进行分组统计
        //val wordAndCountsStream: DStream[(String, Int)] = wordAndOneStream.reduceByKey(_+_)
        //打印统计的结果
        //wordAndCountsStream.print()

        //启动SparkStreamingContext
        streamingContext.start()
        streamingContext.awaitTermination()

    }
}
