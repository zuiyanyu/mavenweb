package scala类和对象04
package dafdsf{

    /**
      * 构造方法
      * scala构造方法比较特殊，和java的很不同
      * scala中的类也是函数，所以类名称后可以加(作为参数列表，表示构造方法，
      */
    object ClassConstractMethod {
        def main(args: Array[String]): Unit = {

            //在scala中，构建对象，等同于调用类的构造方法，执行构造方法体的内容
            val stu1 = new Student();
            println("====================")

            val stu2 = new Student("张三");
            println("====================")

            val stu3 = new Student("张三",20);
        }
    }

    //scala中的类也是函数 ,所以类名称后可以加(作为参数列表，表示构造方法
   //主构造方法
    class Student(){
        //类体 或者 构造方法体
        println("无参数 构造方法体.... " )

        //scala中构造方法可以声明在其他的位置，但是必须调用主构造方法体。比如这里
        //辅助构造方法(必须直接或者间接调用主构造方法)
        def this(name:String ) = {
            this() ;
            println(s"this(name:String ) 构造方法体.... ${name} "  )

        }
        def this(name:String ,age:Int ) = {
            this(name) ;
            println(s"this(name:String ,age:Int ) 构造方法体.... name = $name , age = $age")

        }
    }
}

