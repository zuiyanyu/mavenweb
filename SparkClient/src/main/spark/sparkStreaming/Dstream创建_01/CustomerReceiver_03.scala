package sparkStreaming.Dstream创建_01

import java.io.{BufferedReader, InputStreamReader}
import java.net.Socket
import java.nio.charset.StandardCharsets

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.receiver.Receiver

/**
  * 自定义数据源: 需要继承Receiver，并实现onStart、onStop方法来自定义数据源采集
  * 需求：自定义数据源，实现监控某个端口号，获取该端口号内容。
  */
object CustomerReceiver_03 {
    //TODO 使用自定义的数据源采集数据
    def main(args: Array[String]): Unit = {

        //1.初始化Spark配置信息
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("CustomerReceiver")

        //2.初始化SparkStreamingContext
        val streamingContext = new StreamingContext(sparkConf, Seconds(5))

        //3.创建自定义receiver的Streaming
        val customStream: ReceiverInputDStream[String] = streamingContext.receiverStream(new CustomerReceiver("hadoop103",9999))

        //4.将每一行数据做切分，形成一个个单词
        val wordStream = customStream.flatMap(_.split("\t"))

        //5.将单词映射成元组（word,1）
        val wordAndOneStream = wordStream.map((_, 1))

        //6.将相同的单词次数做统计
        val wordAndCountStream = wordAndOneStream.reduceByKey(_ + _)

        //7.打印
        wordAndCountStream.print()

        //8.启动SparkStreamingContext
        streamingContext.start() //启动处理数据的后台进程
        streamingContext.awaitTermination() //阻塞主进程，等待后台进程处理数据
    }
}

/** TODO 自定义的数据源采集数据
  * TODO abstract class Receiver[T](val storageLevel: StorageLevel)
  *      一个接收器的抽象类，它可以在工作节点上运行以接收外部数据。
  *      一个custom receiver可以通过定义函数' onStart() '和' onStop() '来定义。
  *      “onStart()”应该定义开始接收数据所需的安装步骤，和' onStop() '应该定义停止接收数据所需的清理步骤。
  *      接收时的异常可以通过' restart(…)'重启接收端来处理或完全通过' stop(…)'停止。
  *
  *      T:代表接收器接收的数据类型
  *      StorageLevel：代表接收的数据存储策略。
  *
  * @param host
  * @param port
  */
//TODO abstract class Receiver[T](val storageLevel: StorageLevel)
//Receiver 会一直在后台运行，相当于一个 daemon进程
class CustomerReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY){

    //最初启动的时候，调用该方法，
    // 作用为：读数据并将数据发送给Spark 的BlockManager，BlockManager将数据形成成一个Block数据块保存到其他节点上。
    // block数据会形成一个task任务，就会在保存数据的节点上启动一个Executor进行数据处理。
    override def onStart(): Unit ={
        new Thread("Socket Receiver") {
            override def run() {
                println("启动Receiver，onStart()方法被调用！")
                receive()
            }
        }.start()
    }

    //读数据并将数据发送给 SparkStreamingContext
    def receive(): Unit = {
        println("进入到receive()")

        //创建一个Socket
        var socket: Socket = new Socket(host, port)

        //定义一个变量，用来接收端口传过来的数据
        var input: String = null

        //创建一个BufferedReader用于读取端口传来的数据
        val reader = new BufferedReader(new InputStreamReader(socket.getInputStream, StandardCharsets.UTF_8))

        //读取数据
        input = reader.readLine()
        println("本次启动，接收到的第一条数据:"+input)

        //当receiver没有关闭并且输入数据不为空，则循环发送数据给Spark
        while (!isStopped() && input != null) {
            //将接收到的单个数据项存储到Spark内存中。单个数据项在存储到spark内存中之前，会先被聚合到  data blocks 数据块中。
            //接收的单个数据项 => 聚合到数据块中 => 将聚合的块数据保存到spark内存中。
            store(input)

            //读取下一个数据项
            input = reader.readLine()
        }

        //跳出循环则关闭资源
        reader.close()
        socket.close()

        println("重新启动 Receiver接收器...")
        //重启任务  最终还是会调用 onStart()这个方法
        restart("restart")
        println("重新启动 Receiver接收器完毕！")
    }

    override def onStop(): Unit = {

    }
}
