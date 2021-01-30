package 基础类型操作

import org.junit.Test

object StringOption {
    def main(args: Array[String]): Unit = {
        string_print
    }


    /**
      * 02 raw: 输入字符串的原生样式
      */
    def string_raw():Unit ={
        val xml = raw"<bean id= ''>\n </bean> "
        println(xml)

        //raw 也可以使用 string 插值打印方式
        var name = "World";
        var age:Int = 90 ;
        print(raw"Hello ${name.length} and age + 2 = ${age + 2}\\n")
    }
    /**
      * 1. scala的字符串打印方式：三种
      */
    def string_print()={
        // 打印字符串
        // println 等同于System.out.println("");
        //println("Hello, Scala");

        var name = "World";
        var age:Int = 90 ;
        var money:Double = 200.236

        //方式1：
        println("name is "+ name +"  and age is  "+ age +"  and money =  " + money)

        //格式化打印字符串
        printf("name is  %s  and age is %d and money = %.2f \n", name,age,money)

        // 插值打印字符串    ${表达式} ：代表取表达式的结果值
        print(s"Hello $name \n") // s ：代表使用插值打印
        print(raw"Hello ${name.length} and age + 2 = ${age + 2}\\n") // raw 不会替换字符串中的特殊字符。
    }
}
