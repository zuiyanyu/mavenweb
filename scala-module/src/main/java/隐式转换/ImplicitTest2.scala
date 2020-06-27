package 隐式转换
package dsgagafg{

    /**
      * 隐士转换功能扩展情景1
      * 使用def 进行功能扩展
      */
    object ImplicitTest2 {
        def main(args: Array[String]): Unit = {
            var p:Person = new Person();
            p.login();

            //TODO  隐士转换规则不仅仅是可以转换类型，还可以扩展功能
            implicit def personToUser(pserson:Person): User ={
                new User();
            }
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

