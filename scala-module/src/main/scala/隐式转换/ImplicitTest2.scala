package 隐式转换
package dsgagafg{

    /**
      * 隐士转换功能扩展情景1
      * 使用def 进行功能扩展
      */
    object ImplicitTest2 {
        def main(args: Array[String]): Unit = {
            //TODO 1. 这里仍然属于类型转换，转换的类型进行了功能扩展
            //TODO  隐士转换规则不仅仅是可以转换类型，还可以扩展功能
            implicit def personToUser(pserson:Person): User ={
                new User();
            }

            var p:Person = new Person();
            p.login(); // 这里会进行二次编译，使用隐士转换，将Person类转换为User类
            print(p.getClass)
        }

    }
    class Person {
    }
    class User {
        def login(): Unit ={
            println("user login ...")
        }
    }

}

