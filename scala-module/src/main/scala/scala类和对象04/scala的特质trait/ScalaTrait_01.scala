package scala类和对象04.scala的特质trait
package 特质的初步认识{
    //TODO
    /**
      *
      * TODO 1. java中使用 interface 定义接口，然后使用implements 对接口进行实现： class 类名 implements 接口名1，接口2
      * 1)在Java中, 一个类可以实现多个接口。
      * 2)在Java中，接口之间支持多继承
      * 3)接口中属性都是常量
      * 4)接口中的方法都是抽象的
      *
      * TODO 2. Scala接口的介绍
      * 2.1 从面向对象来看，接口并不属于面向对象的范畴，Scala是纯面向对象的语言，在Scala中，没有接口, 也没有implements关键字。
      * 2.2 Scala语言中，采用trait（特质，特征）来代替接口的概念，
      *      也就是说，多个类具有相同的特征（特征）时，就可以将这个特质（特征）独立出来，采用关键字trait声明。
      *
      * 2.3 特质(trait)的声明
      *     TODO trait 特质名 {
      *     TODO     trait体
      *     TODO }
      *     TODO trait 命名 一般首字母大写.
      *
      * 2.4 TODO 在scala中，java中的接口可以当做特质使用  ； Serializable： 就是scala的一个特质。
      *      object T1 extends Serializable with Cloneable {}
      *TODO 3. 在使用时，采用了extends关键字，如果有多个特质或存在父类，那么需要采用with关键字连接
      *     3.1 没有父类 class  类名   extends   特质1   with    特质2   with   特质3 ..
      *     3.2 有父类   class  类名   extends   父类    with    特质1   with   特质2   with 特质3
      *
      * TODO 4. 如果一个类有父类，同时具有特质，那么执行的时候，会首先执行父类的构造方法，然后执行特质的操作接着执行类的构造方法。
      * TODO 5. 如果同时父类也继承了相同的特质，那么父类的特质先执行，而子类的特质就不会再执行。
      */
    object ScalaTrait_01 {
        def main(args: Array[String]): Unit = {

        }
    }


    trait DbConnection {
        def getConnect(user: String, pwd: String): Unit //声明方法，抽象的.
    }


    class MySql {}
    class B extends MySql {}
    class C extends MySql with DbConnection {
        override def getConnect(user: String, pwd: String): Unit = {
            println("c连接mysql")
        }
    }
    class Oracle
    class E extends Oracle with DbConnection {
        def getConnect(user: String, pwd: String): Unit = {
            println("e连接oracle")
        }
    }

}
