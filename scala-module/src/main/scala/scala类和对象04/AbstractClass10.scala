package scala类和对象04
package dsdfsddf {

    object AbstractClass {
        def main(args: Array[String]): Unit = {

            val parent: Parent = new Son();
            parent.getAge
            parent.getName("小明")
        }
    }

    //声明父类
    abstract class Parent() {

        //声明抽闲属性   如果一个属性没有被初始化，那么就是抽象属性
        var name: String;
        //声明非抽象属性(进行了初始化)
        val age: Int = 20;


        //声明抽象方法
        def getAge: Int;

        //声明非抽象方法
        def getName(name: String): String = {
            println(s"parent name is $name  ...")
            name;
        }
    }

    //声明子类
    class Son() extends Parent {
        //TODO 重写抽象属性 ，重写的是抽象属性，可以不加override, 否则必须加override；
        var name: String = _
        override val age: Int = 30;

        //TODO 类可以重写父类的方法，如果重写的方法时抽象的，那么可以省略override关键字，否则不能省略
        //override
        def getAge: Int = {
            val age = 20;
            println(s"son age is $age")
            age
        }

        override
        def getName(name: String): String = {
            println(s"son name is $name  ...")
            name;
        }

    }
}

