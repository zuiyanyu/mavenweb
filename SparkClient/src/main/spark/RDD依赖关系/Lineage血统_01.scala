package RDD依赖关系

import org.apache.spark.{Dependency, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object Lineage血统_01 {
    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf()
        sparkConf.setAppName("wordCount")
        sparkConf.setMaster("local[2]");

        //2.获取spark的上下文环境  spark-core的核心入口
        val sc = new SparkContext(sparkConf)

        //4. 读取文件中的内容到rdd中
        val wordLine: RDD[String] = sc.textFile("D:\\hadoop\\sparkInput")

        //5.读取一个HDFS文件并将其中内容映射成一个个元组
        val wordAndOne: RDD[(String, Int)] = wordLine.flatMap(_.split("\t")).map((_,1))

        //6.统计每一种key对应的个数
        val wordAndCount = wordAndOne.reduceByKey(_+_)

        //TODO a. 查看血缘关系
        //7. 查看“wordAndOne”的Lineage 血缘
        /**
          * (2) MapPartitionsRDD[3] at map at Lineage血统_01.scala:19 []
          *     |  MapPartitionsRDD[2] at flatMap at Lineage血统_01.scala:19 []
          *     |  D:\hadoop\sparkInput MapPartitionsRDD[1] at textFile at Lineage血统_01.scala:16 []
          *     |  D:\hadoop\sparkInput HadoopRDD[0] at textFile at Lineage血统_01.scala:16 []
          */
        val debugString: String = wordAndOne.toDebugString
        println(debugString)


        //8. （4）查看“wordAndCount”的Lineage
        /**
          * (2) ShuffledRDD[4] at reduceByKey at Lineage血统_01.scala:33 []
          *     +-(2) MapPartitionsRDD[3] at map at Lineage血统_01.scala:19 []
          *         |  MapPartitionsRDD[2] at flatMap at Lineage血统_01.scala:19 []
          *         |  D:\hadoop\sparkInput MapPartitionsRDD[1] at textFile at Lineage血统_01.scala:16 []
          *         |  D:\hadoop\sparkInput HadoopRDD[0] at textFile at Lineage血统_01.scala:16 []
          */
        val debugString2: String = wordAndCount.toDebugString
        println(debugString2)

        //TODO b. RDD和它依赖的父RDD（s）的关系有两种不同的类型，即窄依赖（narrow dependency）和宽依赖（wide dependency）。
        //8. 查看“wordAndOne”的依赖类型 父:子 = 1:1 是1对1关系 窄依赖
        val wordAndOne_dependencies: Seq[Dependency[_]] = wordAndOne.dependencies
        println(wordAndOne_dependencies.mkString(",")) //org.apache.spark.OneToOneDependency@7f37b6d9

        //9. 查看“wordAndCount”的依赖类型  父:子 = 1:N 是一对多关系 宽依赖
        val wordAndCount_dependencies: Seq[Dependency[_]] = wordAndCount.dependencies
        println(wordAndCount_dependencies.mkString(","))  //org.apache.spark.ShuffleDependency@71e35c4


    }
}
