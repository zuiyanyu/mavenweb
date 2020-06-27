package 隐式转换

/**
  * 隐士转换功能扩展情景2
  * 使用class类进行隐士转换功能扩展，而不是def 函数
  *
  * 隐士转换在scala编译时候进行查找，默认当前作用域范围
  * 如果当前作用域范围查找不到，从上级的伴生对象 ,包对象查找。
  */
object ImplicitTest4 {
    def main(args: Array[String]): Unit = {
        type1()  // comming into User1 ....
        type2()  // comming into User2 ....
    }
    //TODO 隐士转换功能扩展情景1
    def type2()={
        //要对父类进行功能扩展
        class Person{
        }
        //TODO 方式2 通过 implicit def  对 person类进行功能扩展
        //TODO 这种方式可以整合多个类的功能到一起
        class User2(){
            def logIn(): Unit ={
                println("comming into User2 ....")
            }
        }
        implicit def userToPerson(person:Person):User2 ={
            new User2()   ;
        }
        val person:Person = new Person();
        //person中没有logIn方法，但是却可以使用logIn()方法
        person.logIn();  //
    }
    //TODO 隐士转换功能扩展情景2
    def type1(): Unit ={
        //要对父类进行功能扩展
        class Person{
        }
        //TODO 方式1：使用隐士转换类(scala2.0后增加) implicit class 对 person类进行功能扩展
        //TODO  这种方式可以特意为某个类进行功能扩展 (声明时候必须有一个参数的构造方法，把此参数隐士转换为当前类)
        implicit class User1(p:Person){
            def logIn(): Unit ={
                println("comming into User1 ....")
            }
        }
        val person:Person = new Person();
        //person中没有logIn方法，但是却可以使用logIn()方法
        person.logIn();  //
    }

}
