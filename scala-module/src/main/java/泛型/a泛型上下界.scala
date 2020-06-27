package 泛型

object a泛型上下界 {
    def main(args: Array[String]): Unit = {
        val aaaa = new AAAA()
        val bbbb = new BBBB()
        val cccc = new CCCC

//        sj(aaaa)
    }
    //TODO 3. scala泛型的上界  等价java的 ？extends BBBB
    def xj[T <: BBBB](t:T): Unit ={

    }

    //TODO 2. scala泛型的下界   等价java的 ？super BBBB
    def sj[T >: BBBB](t:T): Unit ={

    }

    //TODO 1. 泛型基本使用
    def getObj[T](t :T ): T ={
        t
    }
    def getObj[T](t :Class[T] ): T ={
        t.newInstance()
    }
    class AAAA{

    }
    class BBBB extends AAAA{

    }
    class CCCC extends BBBB{

    }
}

