package scala类和对象04
package 伴生对象的apply方法 {

    object ClassApplyMethodTest {
        def main(args: Array[String]): Unit = {
            println("============1============")
            //TODO 1.使用 new 关键字创建伴生类对象
             val stu:Student = new Student();
             stu.printName("李四")


            println("============2============")
            //TODO  2.通过伴生对象 获取伴生类对象  使用静态方式获取实例
            //调用applay来实例化对象
            val stu2 = Student //不会自动调用 Student.apply()
            println("stu2=" + stu2)
            //stu2.printName("李四") // 不能调用

            val stu2Apply = Student.apply // 会调用Student.apply()
            println("stu2Apply=" + stu2Apply)
            stu2Apply.printName("张三")


            println("==========3==============")
            //TODO 使用很多的情况
            val stu4 = Student("王五") // 会调用Student.apply(name :String )
            stu4.printName("王五")
        }
    }

    class Student {
        def printName(name: String)  = {
           print(s"name is $name")
        }
    }

    /**
      * 伴生对象
      *
      * 伴生对象中可以声明applay方法，可以通过调用此方法来构建伴生类对象
      * 这种方式构建对象时候，是可以以不需要使用 new 关键字的。
      */
    object Student {
        def apply(): Student = {
            println("comming into Student.apply()")
            new Student()
        }

        def apply(name: String): Student = {
            println("comming into Student.apply(name:String) ：name = " + name)
            new Student()
        }
    }

}

