package 泛型

import 泛型.a泛型上下界.{AAAA, BBBB}

/**
  * //TODO  协变逆变解决的是类DDDD的数组存储值的元素类型问题。
  * class DDDD[+AAAA] {}
  *
  * //TODO 泛型上下界解决的是类DDDD的实例对象成员对象类型的问题。
  * class DDDD2[T>:AAAA] {}
  */
object a泛型上下界 {
    def main(args: Array[String]): Unit = {
        val aaaa = new AAAA()
        val bbbb = new BBBB()
        val cccc = new CCCC()

        //TODO 下限
        //sj[BBBB](aaaa) //报错
        sj[BBBB](bbbb)
        sj[BBBB](cccc) //不知为何，CCCC是BBBB的子类，却也能传递成功
        sj(aaaa) // 这样可以成功传递,会忽略泛型？

        //TODO 上限
        //xj(aaaa); //报错 没有忽略泛型
        xj(bbbb);
        xj(cccc);


    }


    //TODO 3. scala泛型的上界  等价java的 ？extends BBBB
    //TODO ？能代表的最上层类型为BBBB，所以任何
    //TODO 只能传递 BBBB或者BBBB的父类
    def xj[T <: BBBB](t:T): Unit ={
        //可以调用BBBB类中的方法
        println("sj:"+t.getInfo)
    }

    //TODO 2. scala泛型的下界   等价java的 ？super BBBB
    //TODO 只能传递BBBB 或BBBB的子类
    def sj[T >: BBBB](t:T): Unit ={
        //不可以调用BBBB类中的方法，因为入参是BBBB或其父类，无法确定固定的方法
        println("sj:"+t.getClass.getName)
    }

    //TODO 1. 泛型基本使用
    def getObj[T](t :T ): T ={
        t
    }
    def getObj[T](t :Class[T] ): T ={
        t.newInstance()
    }

    //T 是一个函数，入参是 BBBB类，出参为空。
//    def getObj23[T : BBBB =>String](fun: T)= {
//       ""
//    }




    class AAAA{
        def getInfo:String ={
            "AAAA"
        }
    }
    class BBBB extends AAAA{
        override def getInfo: String = {
            "BBBB"
        }
    }
    class CCCC extends BBBB{
        override def getInfo:String ={
            "CCCC"
        }
    }


}

