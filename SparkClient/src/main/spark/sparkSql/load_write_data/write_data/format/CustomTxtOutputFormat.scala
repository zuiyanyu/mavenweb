package sparkSql.load_write_data.write_data.format

import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.mapred.{FileOutputFormat, JobConf}
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat


class CustomTextOutputFormat extends MultipleTextOutputFormat[Any, Any] {
    //重写generateFileNameForKeyValue方法，该方法是负责自定义生成文件的文件名
    override def generateFileNameForKeyValue(key: Any, value: Any, name: String): String = {

        //这里的key和value指的就是要写入文件的rdd对，再此，我定义文件名以key.txt来命名，当然也可以根据其他的需求来进行生成文件名
        val fileName = key.asInstanceOf[String] + ".txt"
        //key = person ; value = wangw,24,23.5 ;name = part-00001  ; fileName = person.txt
        println(s"key = $key ; value = $value ;name = $name  ; fileName = $fileName" )
        fileName
    }

    /**
      * 因为saveAsHadoopFile是以key,value的形式保存文件，写入文件之后的内容也是，按照key value的形式写入，k,v之间用空格隔开，
      * 这里我只需要写入value的值，不需要将key的值写入到文件中，所以我需要重写该方法，让输入到文件中的key为空即可，
      * 当然也可以进行灵活的变通，也可以重写generateActuralValue(key:Any,value:Any),根据自己的需求来实现
      */
    //写文件的时候，不写出key
    override def generateActualKey(key: Any, value: Any): String = {
        null
    }

    //对生成的value进行转换为字符串，当然源码中默认也是直接返回value值，如果对value没有特殊处理的话，不需要重写该方法
    override def generateActualValue(key: Any, value: Any): String = {
        return value.asInstanceOf[String]
    }

    /**
      * 该方法用来检查我们输出的文件目录是否存在，源码中，是这样判断的，如果写入的父目录已经存在的话，则抛出异常
      * 在这里我们重写这个方法，修改文件目录的判断方式，如果传入的文件写入目录已存在的话，直接将其设置为输出目录即可，
      * 不会抛出异常
      */
    override def checkOutputSpecs(ignored: FileSystem, job: JobConf): Unit = {
        var outDir: Path = FileOutputFormat.getOutputPath(job)
        if (outDir != null) {
            //注意下面的这两句，如果说你要是写入文件的路径是hdfs的话，下面的两句不要写，或是注释掉，它俩的作用是标准化文件输出目录，
            //根据我的理解是，他们是标准化本地路径，写入本地的话，可以加上，本地路径记得要用file:///开头，比如file:///E:/a.txt
            //val fs: FileSystem = ignored
            //outDir = fs.makeQualified(outDir)
            FileOutputFormat.setOutputPath(job, outDir)
        }
    }
}
