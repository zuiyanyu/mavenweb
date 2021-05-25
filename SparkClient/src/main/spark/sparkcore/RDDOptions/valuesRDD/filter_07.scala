package sparkcore.RDDOptions.valuesRDD

//TODO 作用：过滤。返回一个新的RDD，该RDD由经过func函数计算后返回值为true的输入元素组成。
object filter_07  extends  SC {
    //TODO  需求：创建一个RDD（由字符串组成），过滤出一个新RDD（包含”xiao”子串）
    def main(args: Array[String]): Unit = {
        var sourceFilter = sc.parallelize(Array("xiaoming","xiaojiang","xiaohe","dazhi"))
        val result: Array[String] = sourceFilter
                .filter(_.contains("xiao"))
                .collect()
        listPrint(result)
    }
}
