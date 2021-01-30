package scala类和对象04
package jdajfdkj{
    object ClassApplyMethodTest {
        def main(args: Array[String]): Unit = {
            //使用 new 关键字创建对象
            //        val stu:Student = new Student();
            //        stu.getName("李四")

            //TODO 通过伴生对象 获取伴生类  使用静态方式获取实例
            //调用applay来实例化对象
            val stu2  = Student       //不会自动调用 Student.apply()
            println("stu2=" + stu2 )

            val stu2Apply = stu2.apply   // 会调用Student.apply()
            println("stu2Apply="+stu2Apply)
            stu2Apply.getName("张三")

            //TODO 使用很多的情况
            val stu4  = Student("王五")  // 会调用Student.apply(name :String )
            stu4.getName("王五")
        }
    }

    class Student{
        def getName(name :String ) :String ={
            println("=== "+name );
            name ;
        }
    }

    /**
      * 伴生对象
      *
      * 伴生对象中可以声明applay方法，可以通过调用此方法来构建伴生类对象
      * 这种方式构建对象时候，是可以以不需要使用 new 关键字的。
      */
    object Student{
        def apply(): Student ={
            println("comming into Student.apply()" )
            new Student()
        }
        def apply(name:String): Student ={
            println("comming into Student.apply(name:String) ：name = "+ name )
            new Student()
        }
    }
}

