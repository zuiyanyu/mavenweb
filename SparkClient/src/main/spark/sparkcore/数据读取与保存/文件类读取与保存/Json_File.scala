package sparkcore.数据读取与保存.文件类读取与保存

import sparkcore.SC.SC

/**
  * 如果JSON文件中每一行就是一个JSON记录，那么可以通过将JSON文件当做文本文件来读取，然后利用相关的JSON库对每一条数据进行JSON解析。
  * 注意：使用RDD读取JSON文件处理很复杂，同时SparkSQL集成了很好的处理JSON文件的方式，所以应用中多是采用SparkSQL处理JSON文件。
  */
object Json_File extends SC{
    def main(args: Array[String]): Unit = {
        //（1）导入解析json所需的包
        import scala.util.parsing.json.JSON
        JSON.globalNumberParser = {input : String => input}
        JSON.perThreadNumberParser ={input : String => input}

        //（3）读取文件
        val json = sc.textFile("D:\\hadoop\\jsonFile\\input\\persons.txt")

        //TODO （4）解析json数据
        val result  = json.map(JSON.parseFull)

        //（5）打印
        result.collect().foreach(println)
    }
}
