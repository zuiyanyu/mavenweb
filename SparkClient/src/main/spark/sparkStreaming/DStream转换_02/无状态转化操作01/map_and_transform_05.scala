package sparkStreaming.DStream转换_02.无状态转化操作01

import org.apache.spark.streaming.dstream.DStream

object map_and_transform_05 {
    //TODO 探究 DStream的 map算子 和 transform的区别于用途
    //TODO 最大的区别就是代码的执行次序。
    def main(args: Array[String]): Unit = {
        map(null);
        transform(null);
    }

    /**
      *  transform可以将DStream包装好的RDD抽象出来进行转换操作。
      */
    //def transform[U: ClassTag](transformFunc: RDD[T] => RDD[U]): DStream[U]
    def transform[T](stream:DStream[T])={
        //TODO AAAAA代码01  // (Driver) * 1 只在Driver端运行一次
        stream.print(2)
        stream.transform{
            rdd =>{
                //TODO BBBBB 代码02  // (execotor) * N  在executor端运行N次。每个流中的RDD执行前都会运行一次 ，
                                    // 可动态获从外界(比如MySql,Redis等)获取数据
                rdd.map{
                   case (word,sum:Int )=>{
                       //TODO CCCCC代码03  // (execotor) * N  在executor端运行N次。每个元素都会运行一次
                       (word,sum+1)
                   }
                }
            }
        }
    }
    def map[T](stream:DStream[T])={
        //TODO xxxx代码01  // (Driver) * 1 只在Driver端运行一次
        stream.map{
            case (word,sum) =>{
                //TODO yyyyy 代码02  // (execotor) * N  在executor端运行N次。每个元素都会运行一次
                (word,1)
            }
        }
    }
}

























