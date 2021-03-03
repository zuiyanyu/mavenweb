package sparkSql.load_write_data.write_data

import java.util.Properties

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, SQLContext, SparkSession, functions}
import sparkSql.load_write_data.write_data.format.CustomTextOutputFormat

object df_write_txt extends DF_Data {
    def custom_file_name: Unit = {
        val df: DataFrame = getDF
        df.write.format("txt").option("", "")
    }

    private val spark = SparkSession.builder()
            .appName("SparkRead")
            .master("local[2]")
            .getOrCreate()

    private val sc: SparkContext = spark.sparkContext
    private val sqlContext: SQLContext = spark.sqlContext

    import spark.implicits._

    //TODO 自定义txt的输出文件名
    def main(args: Array[String]): Unit = {

        //        val conf = new SparkConf().setAppName(this.getClass.getName).setMaster("local[2]")
        //        val sc = new SparkContext(conf)
        //        val sqlContext = new SQLContext(sc)

        //配置输出文件不生成success文件
        sc.hadoopConfiguration.set("mapreduce.fileoutputcommitter.marksuccessfuljobs", "false")
        //配置一些参数
        //如果设置为true，sparkSql将会根据数据统计信息，自动为每一列选择单独的压缩编码方式
        sqlContext.setConf("spark.sql.inMemoryColumnarStorage.compressed", "true")
        //控制列式缓存批量的大小。增大批量大小可以提高内存的利用率和压缩率，但同时也会带来OOM的风险
        sqlContext.setConf("spark.sql.inMemoryColumnarStorage.batchSize", "1000")
        sqlContext.setConf("spark.sql.autoBroadcastJoinThreshold", "10485760")
        //设为true，则启用优化的Tungsten物理执行后端。Tungsten会显示的管理内存，并动态生成表达式求值得字节码
        sqlContext.setConf("spark.sql.tungsten.enabled", "true")
        //配置shuffle时使用的分区数
        sqlContext.setConf("spark.sql.shuffle.partitions", "200")

        sc.setLogLevel("WARN")
        val rdf: DataFrame = getDF
        val x = rdf.groupBy(functions.substring(functions.col("name"), 0, 5).as("name"))
                .agg(functions.max("age") as ("max"), functions.avg("age") as ("avg"))
                // 写入文件的时候，是以 key value的形式
                .rdd.map(x => ("person", x(0) + "," + x(1) + "," + x(2)))
                //这里partitionBy，只是来增加文件文件写入的并行度，可以根据需求进行设置，影响的是文件写入的性能，和文件的数量
                .partitionBy(new HashPartitioner(2))
                //这里写入的时候，要指定我们自定义的PairRDDMultipleTextOutputFormat类
                .saveAsHadoopFile("file:///D:/hadoop/sparkSql/res", classOf[String], classOf[String], classOf[CustomTextOutputFormat])
        sc.stop()
    }
}
