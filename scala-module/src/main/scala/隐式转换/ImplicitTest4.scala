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
        type1()  // comming into User2 ....
        type2()  // comming into User1 ....
    }
    //TODO 隐士转换功能扩展情景1
    def type1()={
        //要对父类进行功能扩展
        class Person{
        }
        class User2(){
            def logIn(): Unit ={
                println("comming into User2 ....")
            }
        }

        //TODO 方式1 通过 implicit def  对 person类进行功能扩展
        //TODO 这种方式可以整合多个类的功能到一起
        //TODO 表示无关两个类A,B 之间方法的调用关系
        implicit def userToPerson(person:Person):User2 ={
            new User2()   ;
        }
        val person:Person = new Person();
        //person中没有logIn方法，但是却可以使用logIn()方法
        person.logIn();  //
    }
    //TODO 隐士转换功能扩展情景2
    def type2(): Unit ={
        //要对父类进行功能扩展
        class Person{
        }
        //TODO 3.class类隐士转换   将构造入参的类转换为当前类，可以进行功能扩展
        //TODO 方式2：使用隐士转换类(scala2.0后增加) implicit class 对 person类进行功能扩展
        //TODO 这种方式可以特意为某个类进行功能扩展 (声明时候必须有一个参数的构造方法，把此参数隐士转换为当前类)
        //TODO 代表父类可以使用子类的方法
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
