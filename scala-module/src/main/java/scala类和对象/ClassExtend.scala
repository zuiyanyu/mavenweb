package scala类和对象

package jdjsjdfjsd{
    object ClassExtend {
        def main(args: Array[String]): Unit = {
            val person = new Student1();
            //TODO 父类属性初始化的时候用到子类属性，那么就出出问题：子类属性还没初始化，父类就使用，必出问题。
            println(person.name)  //null张三


            val person2 = new Student2();
            println(person2.name)  //student1_张三


        }
    }
    class Person(){
        val prefix = "person_"
        val name = prefix + "张三"
    }
    //
    class Student1() extends  Person {
        override val prefix = "student1_"
    }

    //TODO 父类属性初始化的时候用到子类属性，那么就必然出问题：子类属性还没初始化，父类就使用，必出问题。
    // 解决方案如下：
    class Student2() extends {
        // 代码块：这段代码必须立即执行进行初始化
        override val prefix = "student1_"
    } with Person {
    }

}


























