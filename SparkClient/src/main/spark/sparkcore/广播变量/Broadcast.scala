package sparkcore.广播变量

import java.util

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import sparkcore.累加器.CustomAccumulator

/**
  * TODO  广播变量用来高效分发较大的对象。
  * TODO 向所有工作节点发送一个较大的只读值，以供一个或多个Spark操作使用。
  *       比如，如果你的应用需要向所有节点发送一个较大的只读查询表，
  *TODO  在多个并行操作中使用同一个变量，但是 Spark会为每个任务分别发送。
  *
  * TODO 使用广播变量的过程如下：
  * (1) 通过对一个类型 T 的对象调用 SparkContext.broadcast 创建出一个 Broadcast[T] 对象。 任何可序列化的类型都可以这么实现。
  *
  * (2) 通过 value 属性访问该对象的值(在 Java 中为 value() 方法)。
  *
  * (3) 变量会被发到各个节点一次，应作为只读值处理(修改这个值不会影响到别的节点)。
  */
object Broadcast {
    def main(args: Array[String]): Unit = {
        test_02
    }
    //基础应用
    def test_02(): Unit ={
        val conf = new SparkConf().setMaster("local[2]").setAppName("LogAccumulator")
        val sc = new SparkContext(conf)
        sc.setLogLevel("ERROR")

        //1. 数据：一些名单
        val rdd: RDD[String] = sc.makeRDD(List("wangwu", "zhaoliu", "zhangsan", "lisi", "spark"))

        //2. 要查找的人
        val names = Array("wangwu","lisi");

        //3. 要查找的人进行广播
        val broadcast_names: Broadcast[Array[String]] = sc.broadcast(names)

        //4. 查找到的人要进行收集起来，所以要用到累加器
        //创建累加器
        val nameAcc: CustomAccumulator[String] = new CustomAccumulator[String]()
        //注册累加器
        sc.register(nameAcc,"findName")

        //5. 过滤掉黑名单，并查找出有哪些黑名单
        rdd.filter(name=>{
            //获取广播变量
            val namesToFind: Array[String] = broadcast_names.value
            val isFinded: Boolean = namesToFind.contains(name)
            //累加器 收集被过滤的黑名单
            if(isFinded){
                nameAcc.add(name);
            }
            //没有发现的名字放行
            !isFinded
        }).collect().foreach(println);

        println("=========累加器的收集结果==============")
        val blockNames: util.Set[String] = nameAcc.value
        val blockNamesIter: util.Iterator[String] = blockNames.iterator()
        while(blockNamesIter.hasNext){
            val name: String = blockNamesIter.next()
            println(name)
        }
        sc.stop();

        /**
          * zhaoliu
          * zhangsan
          * spark
          * //=========累加器的收集结果==============
          * lisi
          * wangwu
          */
    }
    //基础语法
    def test_01: Unit ={
        val conf = new SparkConf().setMaster("local[2]").setAppName("LogAccumulator")
        val sc = new SparkContext(conf)
        sc.setLogLevel("ERROR")

        //TODO 开始广播变量到各个节点
        val broadcastVar: Broadcast[Array[Int]] = sc.broadcast(Array(1, 2, 3))

        //TODO 获取广播变量的值
        val value: Array[Int] = broadcastVar.value

        println(value)
    }

}
