package sparkcore.数据读取与保存.文件类读取与保存

import sparkcore.SC.SC

/**
  *  对象文件:
  * 对象文件是将对象序列化后保存的文件，采用Java的序列化机制。可以通过objectFile[k,v](path) 函数接收一个路径，
  * 读取对象文件，返回对应的 RDD，也可以通过调用saveAsObjectFile() 实现对对象文件的输出。因为是序列化所以要指定类型。
  */
object Object_File extends SC{
    def main(args: Array[String]): Unit = {
        val rdd = sc.parallelize(Array(1,2,3,4))

        val filePath ="D:\\hadoop\\objectFile\\obj"
        //2）将RDD保存为Object文件
        rdd.saveAsObjectFile(filePath)

        //（4）读取Object文件
        val objFile = sc.objectFile[Int](filePath)

        //（5）打印读取后的Sequence文件
        objFile.collect.foreach(println)
    }
}
