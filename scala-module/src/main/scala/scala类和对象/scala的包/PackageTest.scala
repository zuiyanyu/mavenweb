package scala类和对象.scala的包

//TODO scala可以将package 当成关键字用，但是包中可以放类和接口，因为受到了JVM限制
//TODO scala为了让开发方便，使用了包对象的概念。其中可以声明属性和方法，类似于伴生对象
package a{
    //包对象  (可以和包的名字一样)
    package object b {
        var userName = "张三李四" ;
    }
    //包
    package b {
        //包中声明类，但是包中不能声明属性
        class User{
            def getUserName(name :String ):String={
                //使用包对象中的 属性
                println(s"name = $name   and b.userName = ${b.userName}")
                return  name ;
            }
        }
    }
    //TODO 父包使用子包中的类需要进行导包，反之不用。
    import scala类和对象.scala的包.a.b.User
    class Users{
        val user = new User();
        def getUserName(name :String ): Unit ={
            user.getUserName(name) ;
        }
    }
}

import scala类和对象.scala的包.a.Users
object PackageTest {
    def main(args: Array[String]): Unit = {
        val users = new Users();
        users.getUserName("张三")
    }
}
