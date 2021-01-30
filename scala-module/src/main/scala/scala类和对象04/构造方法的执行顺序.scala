package scala类和对象04
package djsjfosdjf{
    object 构造方法的执行顺序 {
        def main(args: Array[String]): Unit = {

            val person :Person = new Student1

        }
    }
    class Person{
        println("Person 构造方法....")
    }
    class Student1 extends  Person {
        println("Student1 构造方法...")
    }
}

