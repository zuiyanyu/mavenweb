package sparkSql.load_write_data.formats.csvformat

import org.apache.hadoop.fs.FileStatus
import org.apache.hadoop.mapreduce.Job
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.execution.datasources.csv.CSVFileFormat
import org.apache.spark.sql.execution.datasources.{OutputWriterFactory, TextBasedFileFormat}
import org.apache.spark.sql.sources.DataSourceRegister
import org.apache.spark.sql.types.StructType
//class MyCSVFileFormat extends DataSourceRegister{
class MyCSVFileFormat extends CSVFileFormat  {
    override def shortName(): String = "mycsv"
}
