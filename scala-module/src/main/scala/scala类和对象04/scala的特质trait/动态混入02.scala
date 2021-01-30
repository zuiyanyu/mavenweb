package scala类和对象04.scala的特质trait
package 特质的动态混入{
    /**
      * TODO 带有特质的对象，动态混入
      *   1) TODO 除了可以在类声明时继承特质以外，还可以在构建对象时混入特质，扩展对象的功能
      *   2) TODO 此种方式也可以应用于对抽象类功能进行扩展
      *   3) TODO 动态混入可在不修改类声明/定义的情况下，扩展类的功能，非常的灵活，耦合性低 。
      *   4)      动态混入可以在不影响原有的继承关系的基础上，给指定的类扩展功能。
      */
    //TODO  动态混入可在不修改类声明/定义的情况下，扩展类的功能
    object 动态混入02 {
        def main(args: Array[String]): Unit = {

            //TODO 动态混入在构建对象时混入特质，扩展对象的功能
            class MySQL

            val mysql = new MySQL with  DB
            mysql.insert(4)
        }
    }
    // TODO 特质中的方法可以是抽象的，也可以是具体实现的。
    trait Operate  {
        def insert(id: Int): Unit = {
            println( "插入数据 = " + id)
        }
    }
    trait DB extends Operate {
       override def insert(id: Int): Unit = {
            print( "向数据库中"  )
            super.insert(id)
        }
    }
    trait File extends Operate {
        override def insert(id: Int): Unit = {
            print( "向file文件中"  )
            super.insert(id)
        }
    }

}
