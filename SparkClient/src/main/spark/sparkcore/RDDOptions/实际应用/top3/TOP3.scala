package sparkcore.RDDOptions.实际应用.top3

import sparkcore.RDDOptions.valuesRDD.SC
import org.apache.spark.rdd.RDD

/**
  * TODO  需求：统计出每一个省份广告被点击次数的TOP3
  * 1. 数据结构：时间戳，省份，城市，用户，广告，中间字段使用空格分割。
  * 样本如下：
  * 1516609143867 6 7 64 16
  * 1516609143869 9 4 75 18
  * 1516609143869 1 7 87 12
  */
object TOP3 extends SC{
    // TODO  需求：统计出每一个省份广告被点击次数的TOP3
    /**
      * 需求分析：
      *   粒度 ：省份 + 广告
      *   比较值：广告点击量
      * 实现思路： (省份-广告,1) => (省份-广告,广告点击量) => （省份,（广告，广告点击量）)
      *             => 根据 省份分组，组内将value整合到iter中，根据广告点击量倒序排序 ，并只保留前三个 => 输出结果
      * @param args
      */
    def main(args: Array[String]): Unit = {
        type_02
    }


    def type_01: Unit ={
        val logFilePath = "D:\\hadoop\\key_value_RDD\\agent.log";
        //1. 读取日志文件，形成rdd
        val logRDD: RDD[String] = sc.textFile(logFilePath)
        println(logRDD.count())  //4957

        //2. 进行解析映射为 (省份-广告,1)
        val shengAndguangGao_1: RDD[(String, Int)] = logRDD.map(line => {
            //数据结构：时间戳，省份，城市，用户，广告，中间字段使用空格分割。
            val splits: Array[String] = line.split(" ")
            val sheng = splits(1);
            val guangGao = splits(splits.length - 1)

            (sheng + "-" + guangGao, 1)   //(省份-广告,1)
        })
        listPrint(shengAndguangGao_1.take(2))  // [ (6-16,1),(9-18,1) ]

        //2. 区内进行预聚合，区间再进行聚合，得到 (省份-广告,广告点击量)
        val shengAndguangGao_counts: RDD[(String, Int)] = shengAndguangGao_1.reduceByKey(_+_)

        //3. 要根据省份进行分组，所以调整结构，得到（省份,（广告，广告点击量）)
        val sheng_guangGaoAndCounts: RDD[(String, (String, Int))] = shengAndguangGao_counts.map
        { case (sheng_guangGao, counts) =>
            val splits: Array[String] = sheng_guangGao.split("-")
            val sheng = splits(0);
            val guangGao = splits(1);
            (sheng, (guangGao, counts)) // （省份,（广告，广告点击量）)
        }

        //4. 区内根据key（省份）进行分组聚合，然后排序，过滤出前三
        val groupBySheng: RDD[(String, Iterable[(String, Int)])] = sheng_guangGaoAndCounts.groupByKey()
        val top3RDD: RDD[(String, Iterable[(String, Int)])] = groupBySheng.map {
            case (sheng, iter) =>
                //根据广告量倒序排序
                val sortedArray: Array[(String, Int)] = iter.toArray.sortBy(-_._2)
                //println("排序前："+sortedArray.mkString(","))
                //取出省内点击量前三的广告
                val top3Array: Array[(String, Int)] = sortedArray.take(3)
                //println("top3 :" + top3Array.mkString(","))

                //省，前三名的广告
                (sheng, top3Array.toIterable)
        }
        /*  其他写法
        //4.对同一个省份所有广告的集合进行排序并取前3条，排序规则为广告点击总数
        val provinceAdTop3 = groupBySheng.mapValues { x =>
            x.toList.sortWith((x, y) => x._2 > y._2).take(3)
        }
        */
        //5.打印结果
        val tuples: Array[(String, Iterable[(String, Int)])] = top3RDD.collect()

        for (tuple <- tuples) {
            val sheng = tuple._1
            val iter: Iterable[(String, Int)] = tuple._2
            println( sheng + " [" + iter.toArray.mkString(",") + "]" )
        }

        /**  结果： 省 ：（广告，点击量）
          * 8 [(2,27),(20,23),(11,22)]
          * 4 [(12,25),(16,22),(2,22)]
          * 6 [(16,23),(24,21),(27,20)]
          * 0 [(2,29),(24,25),(26,24)]
          * 2 [(6,24),(21,23),(29,20)]
          * 7 [(16,26),(26,25),(1,23)]
          * 5 [(14,26),(21,21),(12,21)]
          * 9 [(1,31),(28,21),(0,20)]
          * 3 [(14,28),(28,27),(22,25)]
          * 1 [(3,25),(6,23),(5,22)]
          */
    }

    //看看其他人的写法，思路和我的是一样的
    def type_02: Unit ={
        val logFilePath = "D:\\hadoop\\key_value_RDD\\agent.log";
        //2. 读取数据生成RDD：TS，Province，City，User，AD
        val line: RDD[String] = sc.textFile(logFilePath)
        println(line.count())  //4957

        //3.按照最小粒度聚合：((Province,AD),1)
        val provinceAdToOne = line.map { x =>
            val fields: Array[String] = x.split(" ")
            ((fields(1), fields(4)), 1)
        }
        //4.计算每个省中每个广告被点击的总数：((Province,AD),sum)
        val provinceAdToSum = provinceAdToOne.reduceByKey(_ + _)

        //5.将省份作为key，广告加点击数为value：(Province,(AD,sum))
        val provinceToAdSum = provinceAdToSum.map(x => (x._1._1, (x._1._2, x._2)))

        //6.将同一个省份的所有广告进行聚合(Province,List((AD1,sum1),(AD2,sum2)...))
        val provinceGroup = provinceToAdSum.groupByKey()

        //7.对同一个省份所有广告的集合进行排序并取前3条，排序规则为广告点击总数
        val provinceAdTop3 = provinceGroup.mapValues { x =>
            x.toList.sortWith((x, y) => x._2 > y._2).take(3)
        }
        //8.将数据拉取到Driver端并打印
        provinceAdTop3.collect().foreach(println)

        //9.关闭与spark的连接
        sc.stop()

        /**
          * (4,List((12,25), (2,22), (16,22)))
          * (8,List((2,27), (20,23), (11,22)))
          * (6,List((16,23), (24,21), (22,20)))
          * (0,List((2,29), (24,25), (26,24)))
          * (2,List((6,24), (21,23), (29,20)))
          * (7,List((16,26), (26,25), (1,23)))
          * (5,List((14,26), (21,21), (12,21)))
          * (9,List((1,31), (28,21), (0,20)))
          * (3,List((14,28), (28,27), (22,25)))
          * (1,List((3,25), (6,23), (5,22)))
          */


    }

}
