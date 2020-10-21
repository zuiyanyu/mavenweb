package 泛型

import 泛型.a泛型上下界.BBBB

/**
  * 1.在这里引入关于这个符号的说明，在声明Scala的泛型类型时，“+”表示协变，而“-”表示逆变 
  *   C[+T]：如果A是B的子类，那么C[A]是C[B]的子类，称为协变 
  *   C[-T]：如果A是B的子类，那么C[B]是C[A]的子类，称为逆变 
  *   C[T]：无论A和B是什么关系，C[A]和C[B]没有从属关系。称为不变.
  *
  *2. 上下界是针对入参类型的限定：上界 :<  是 协变 (找子类型)；  下界 >: 是逆变 (找父类型)
  *
  *3. 协变、逆变、不变 是对变量类型的限定。
  *
  *4. 上下界 和 协逆变 还是有所区别的。
  */
object b泛型的协变逆变 {
    def main(args: Array[String]): Unit = {
        //TODO 斜边：从当前的类往下找，找子类型 ； 逆变：从子类型往上找，找父类型。
        //TODO 上界 :<  也是 协变 (找子类型)；  下界 >: 也是逆变 (找父类型)；
        //TODO 不变：

        //TODO 协变使用实例 :变量声明时候， DDDD的泛型是 BBBB，但是可以用CCCC的子类来代替 BBBB
        val dddd2:DDDD[BBBB] = new DDDD[BBBB] ;  // 正常声明
        val dddd1:DDDD[BBBB] = new DDDD[CCCC] ; // 可以：因为CCCC是BBBB的子类，所以 DDDD[CCCC] 是DDDD[BBBB]的子类

        //TODO 逆变的实例
        val eeee2: EEEE[BBBB] = new EEEE[BBBB];
        val eeee3: EEEE[CCCC] = new EEEE[BBBB];  // 因为BBBB是CCCC的父类，所以 EEEE[BBBB]是 EEEE[CCCC]的子类。


        //TODO 不变的实例
        val ffff:FFFF[BBBB] = new FFFF[BBBB]
        //val ffff2:FFFF[BBBB] = new FFFF[CCCC] //报错
        //val ffff2:FFFF[BBBB] = new FFFF[AAAA] //报错
        val f     = new FFFF[CCCC]  //但是这样却可以，会忽略泛型
        f.printInfo()  //FFFF
     }
    //TODO 协变
    class DDDD[+BBBB]{
    }

    //TODO 上下界 和 协逆变 的区别。
    //TODO 逆变 ：接受父类型。 这里的父子类型是针对 EEEE[BBBB父类型] 的。
    class EEEE[-BBBB]{
    }
    //TODO 下界：接受BBBB的父类型。这里的父子类型是针对 BBBB而言 。
    class EEEE2[T >: BBBB]{
    }

    //TODO 不变   类似 List[String]
    class FFFF[BBBB]{
        def printInfo(): Unit ={
            println("FFFF");
        }
    }

    class AAAA{
        def printInfo(): Unit ={
            println("AAAA");
        }
    }
    class BBBB extends AAAA{
        override def printInfo(): Unit ={
            println("BBBB");
        }
    }
    class CCCC extends BBBB{
      override def printInfo(): Unit ={
          println("CCCC");
      }
    }


}
