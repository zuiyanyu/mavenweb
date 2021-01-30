package scala类和对象04.scala的特质trait
package 特质的执行顺序{
    object ScalaTrait_03 {
        //TODO  动态混入可在不修改类声明/定义的情况下，扩展类的功能
        def main(args: Array[String]): Unit = {

            //TODO 1. 动态混入在构建对象时混入特质，扩展对象的功能
            class MySQL{
                println("mysql构造器")
            }
            println("============1================")
            val mysql = new MySQL with  DB  with File
            mysql.insert(4)  //向file文件中向数据库中插入数据 = 4

            println("=============2===============")
            val mysql2 = new MySQL  with File with  DB
            mysql2.insert(4)  //向数据库中向file文件中插入数据 = 4

            /**
              * TODO 2. 特质的继承顺序不同，执行结果也不同，规律如下 ：
              * TODO 2.1 构造器执行顺序：从左到右执行   ->  trait 在继承时候，从左到右依次继承
              * TODO 2.2 方法调用顺序：  从右向左调用   -> trait 方法调用，super不是上一级的意思，而是上一个trait。
              *
              * TODO 3.  如果在supper关键字后面增加中括号，其中增加泛型，那么可以调用指定特质的方法。
              *
              * 执行结果：  不是我们期望的结果
              *
              * //============1================ new MySQL with  DB  with File
              * mysql构造器
              * Operate 构造器
              * DB 构造器
              * File 构造器
              * 向file文件中向数据库中插入数据 = 4
              * //=============2=============== new MySQL  with File with  DB
              * mysql构造器
              * Operate 构造器
              * File 构造器
              * DB 构造器
              * 向数据库中向file文件中插入数据 = 4
              *
              */
            //TODO 3.  如果在supper关键字后面增加中括号，其中增加泛型，那么可以调用指定特质的方法。
            println("============3================")
            val mysql3 = new MySQL with  DB_2  with File_2
            mysql3.insert(4)  //向file文件中向数据库中插入数据 = 4

            println("=============4===============")
            val mysql4 = new MySQL  with File_2 with  DB_2
            mysql4.insert(4)  //向数据库中向file文件中插入数据 = 4

            /**
              * 运行结果： 是我们期望的结果
              *
              * //============3================
              * mysql构造器
              * Operate 构造器
              * DB 构造器
              * File 构造器
              * 向file文件中插入数据 = 4
              * //=============4===============
              * mysql构造器
              * Operate 构造器
              * File 构造器
              * DB 构造器
              * 向数据库中插入数据 = 4
              */


        }
    }
    // TODO 特质中的方法可以是抽象的，也可以是具体实现的。
    trait Operate  {
        println("Operate 构造器")
        def insert(id: Int): Unit = {
            println( "插入数据 = " + id)
        }
    }
    //======================1==========================
    trait DB extends Operate {
        println("DB 构造器")

        override def insert(id: Int): Unit = {
            print( "向数据库中"  )
            super.insert(id)
        }
    }
    trait File extends Operate {
        println("File 构造器")

        override def insert(id: Int): Unit = {
            print( "向file文件中"  )
            super.insert(id)
        }
    }
    //======================2==========================
    //TODO 3.  如果在supper关键字后面增加中括号，其中增加泛型，那么可以调用指定特质的方法。
    trait DB_2 extends Operate {
        println("DB 构造器")

        override def insert(id: Int): Unit = {
            print( "向数据库中"  )
            super[Operate].insert(id)
        }
    }
    trait File_2 extends Operate {
        println("File 构造器")

        override def insert(id: Int): Unit = {
            print( "向file文件中"  )
            super[Operate].insert(id)
        }
    }


}

