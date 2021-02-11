package sparkContext

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

class SparkSesstionTest{
    private val builder: SparkSession.Builder = SparkSession.builder()
    // master: local, yarn,master三种模式  ，
    private val spark: SparkSession.Builder =
        builder
            .master("master")
            .appName("appName")
            .enableHiveSupport()
    private val session: SparkSession = spark.getOrCreate()

    private val sc: SparkContext = session.sparkContext
    private val value: RDD[String] = sc.textFile("", 10)
    value.count();
}