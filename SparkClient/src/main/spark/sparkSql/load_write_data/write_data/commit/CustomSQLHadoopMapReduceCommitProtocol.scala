package sparkSql.load_write_data.write_data.commit

import java.util.Date

import org.apache.hadoop.fs.Path
import org.apache.hadoop.mapreduce._
import org.apache.spark.sql.execution.datasources.SQLHadoopMapReduceCommitProtocol


/**
  *自定义csv的输出文件名
   val spark = SparkSession.builder().appName("SparkRead")
            .config("spark.sql.sources.commitProtocolClass","sparkSql.load_write_data.write_data.commit.CustomSQLHadoopMapReduceCommitProtocol")
            .master("local[2]")
            .getOrCreate()

  read_csv.write
                .format("csv")
                .option("header", "true")//保存文件的时候，保存头信息  false:不保存头信息
                .mode(SaveMode.Overwrite) //覆盖原始数据
                .option("fileName", "customFileName") //自定义保存的文件名
                .save(csvSavePath)
  * @param jobId
  * @param path
  * @param isAppend
  */
class CustomSQLHadoopMapReduceCommitProtocol(jobId: String, path: String, isAppend: Boolean)
        extends SQLHadoopMapReduceCommitProtocol(jobId: String, path: String, isAppend: Boolean) with Serializable {

    @transient private var committer: OutputCommitter = _
    
    override def newTaskTempFile(taskContext: TaskAttemptContext, dir: Option[String], ext: String): String = {
        
        //val hadoopConf = sparkSession.sessionState.newHadoopConfWithOptions(options)
        val tmpfileName: String = taskContext.getConfiguration.get("fileName")
        println(s"fileName = $tmpfileName ;dir=${dir.getOrElse("null")} ext = $ext")

       if(tmpfileName ==null){
           return super.newTaskTempFile(taskContext,dir,ext)
        }
        //不同的reduce分区名会写到不同的文件中，需要区分开
        val split = taskContext.getTaskAttemptID.getTaskID.getId
        val fileName = f"$tmpfileName-$split$ext"
        val outPathWithFileName: String = new Path(path,fileName).toString
        println(s"outPathWithFileName = $outPathWithFileName")

        return outPathWithFileName
    }

    import java.io.IOException


  
}

/* read_csv.write.format("csv").save(filepath) 代码追踪逻辑

  这里是将df保存为csv文件的开始程序
  InsertIntoHadoopFsRelationCommand#run(){

      会将除了path 和 paths这两个option配置参数以外的参数都存放到 hadoopConf 中。
      val hadoopConf = sparkSession.sessionState.newHadoopConfWithOptions(options)

      这里里面生成job的提交路径，文件的落地文件名
      val committer = FileCommitProtocol.instantiate(
        sparkSession.sessionState.conf.fileCommitProtocolClass,
        jobId = java.util.UUID.randomUUID().toString,
        outputPath = outputPath.toString,
        isAppend = isAppend)

      这里就是进行文件的写程序，将文件写到hdfs。
      FileFormatWriter.write(
        sparkSession = sparkSession,
        queryExecution = Dataset.ofRows(sparkSession, query).queryExecution,
        fileFormat = fileFormat,
        committer = committer,
        outputSpec = FileFormatWriter.OutputSpec(
          qualifiedOutputPath.toString, customPartitionLocations),
        hadoopConf = hadoopConf,
        partitionColumns = partitionColumns,
        bucketSpec = bucketSpec,
        refreshFunction = refreshFunction,
        options = options)
   }

 ======================================================
 val FILE_COMMIT_PROTOCOL_CLASS =
    SQLConfigBuilder("spark.sql.sources.commitProtocolClass")
      .internal()
      .stringConf
      .createWithDefault(
        "org.apache.spark.sql.execution.datasources.SQLHadoopMapReduceCommitProtocol")

spark.sql.sources.commitProtocolClass
org.apache.spark.sql.execution.datasources.SQLHadoopMapReduceCommitProtocol

outputPath = caseInsensitiveOptions.get("path")

val plan =
  InsertIntoHadoopFsRelationCommand(
    outputPath = outputPath,

case class InsertIntoHadoopFsRelationCommand(
    outputPath: Path)

 val committer = FileCommitProtocol.instantiate(
        sparkSession.sessionState.conf.fileCommitProtocolClass,
        jobId = java.util.UUID.randomUUID().toString,
        outputPath = outputPath.toString,
        isAppend = isAppend)


org.apache.spark.sql.execution.datasources.SQLHadoopMapReduceCommitProtocol 这个方法控制落地文件名。

outputPath =
 // 这里可控制文件的落地文件名
 private def getFilename(taskContext: TaskAttemptContext, ext: String): String = {
    // The file name looks like part-r-00000-2dd664f9-d2c4-4ffe-878f-c6c70c1fb0cb_00003.gz.parquet
    // Note that %05d does not truncate the split number, so if we have more than 100000 tasks,
    // the file name is fine and won't overflow.
    val split = taskContext.getTaskAttemptID.getTaskID.getId
    f"part-$split%05d-$jobId$ext"
  }

  =========================================
// Note: prepareWrite has side effect. It sets "job".
val outputWriterFactory =
  fileFormat.prepareWrite(sparkSession, job, options, dataColumns.toStructType)

def prepareWrite(
      sparkSession: SparkSession,
      job: Job,
      options: Map[String, String],
      dataSchema: StructType): OutputWriterFactory

org.apache.spark.sql.execution.datasources.csv.CSVFileFormat

override def prepareWrite(
      sparkSession: SparkSession,
      job: Job,
      options: Map[String, String],
      dataSchema: StructType): OutputWriterFactory = {
    verifySchema(dataSchema)
    val conf = job.getConfiguration
    val csvOptions = new CSVOptions(options)
    csvOptions.compressionCodec.foreach { codec =>
      CompressionCodecs.setCodecConfiguration(conf, codec)
    }

    new CSVOutputWriterFactory(csvOptions)
  }

private[csv] class CSVOutputWriterFactory(params: CSVOptions) extends OutputWriterFactory {
  override def newInstance(
      path: String,
      dataSchema: StructType,
      context: TaskAttemptContext): OutputWriter = {
    new CsvOutputWriter(path, dataSchema, context, params)
  }


private val recordWriter: RecordWriter[NullWritable, Text] = {
    new TextOutputFormat[NullWritable, Text]() {
      override def getDefaultWorkFile(context: TaskAttemptContext, extension: String): Path = {
        new Path(path)
      }
    }.getRecordWriter(context)
  }

 public RecordWriter<K, V> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        Configuration conf = job.getConfiguration();
        boolean isCompressed = getCompressOutput(job);
        String keyValueSeparator = conf.get(SEPERATOR, "\t");
        CompressionCodec codec = null;
        String extension = "";
        if (isCompressed) {
            Class<? extends CompressionCodec> codecClass = getOutputCompressorClass(job, GzipCodec.class);
            codec = (CompressionCodec)ReflectionUtils.newInstance(codecClass, conf);
            extension = codec.getDefaultExtension();
        }

        // 这里的 file 只是一个文件路径，不是真实要写的文件名。
        Path file = this.getDefaultWorkFile(job, extension);
        FileSystem fs = file.getFileSystem(conf);
        FSDataOutputStream fileOut;
        if (!isCompressed) {
            fileOut = fs.create(file, false);
            return new TextOutputFormat.LineRecordWriter(fileOut, keyValueSeparator);
        } else {
            fileOut = fs.create(file, false);
            return new TextOutputFormat.LineRecordWriter(new DataOutputStream(codec.createOutputStream(fileOut)), keyValueSeparator);
        }
    }
 */
