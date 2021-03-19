package sparkSql.load_write_data.formats.csvformat

import java.util
import java.util.ServiceLoader

import org.apache.spark.sql.sources.DataSourceRegister

object Test {
    def main(args: Array[String]): Unit = {
////        val colName ="333"
////        val value = s""" "$colName" """
////        println(value)
//        val array = Array(1,3)
//        val flat: Array[String] = array.flatMap(_=>Option("1"))
//        val str: String = flat.mkString(",")
//        println(str)
        val loader = Thread.currentThread().getClass.getClassLoader
        val registers: ServiceLoader[DataSourceRegister] = ServiceLoader.load(classOf[DataSourceRegister], loader)
        val iterator: util.Iterator[DataSourceRegister] = registers.iterator()
        while (iterator.hasNext){
            val name: DataSourceRegister = iterator.next()
            println(name.getClass.getName +" = > "+name.shortName())
        }
    }
}
