package RDD中的函数传递

import org.apache.spark.rdd.RDD

//class Search(query:String) extends Serializable {
class Search(query:String)  {
    //过滤出包含字符串的数据
    def isMatch(s: String): Boolean = {
        s.contains(query)
    }

    //RDD过滤出包含字符串的元素
    def getMatch1 (rdd: RDD[String]): RDD[String] = {
        //TODO 传递一个对象方法 isMatch() 到executor,方法所属对象需要序列化
        rdd.filter(isMatch)
    }

    //RDD过滤出包含字符串的元素
    def getMatche2(rdd: RDD[String]): RDD[String] = {
        //TODO 传递一个对象属性 query:String 到executor，属性所属对象需要序列化
        rdd.filter(x => x.contains(query))
    }

    //RDD过滤出包含字符串的元素
    def getMatche3(rdd: RDD[String]): RDD[String] = {
        //TODO 传递一个对象属性的解决方案2
        val query_ : String = this.query//将类变量赋值给局部变量
        rdd.filter(x => x.contains(query_))
    }

}
