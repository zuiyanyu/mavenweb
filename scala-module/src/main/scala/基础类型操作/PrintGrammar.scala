package 基础类型操作

object PrintGrammar {
    private val age = 29 ;
    def main(args :Array[String]){
        val xml = raw"<bean id= ''>\n </bean> "
        println(xml)

        val age = 12 ;
        val info = s"age = ${age}"
        println(info)
        println( s"age = ${age+1}")
    }
}
