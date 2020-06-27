package scala集合.Array集合

object Array二维数组 {
    def main(args: Array[String]): Unit = {
//        arrayDefine
        getElement
    }
    //TODO 2. 遍历
    def getElement(): Unit ={

        var array = Array.ofDim[Int](3,3)
        for(i<- 0 until 3 ; j <- 0 until 3 ){
            array(i)(j) = i+j ;
        }

        for(i<- 0 until array.length;j <- 0 until array(i).length){
            print(s"array($i)($j)= ${array(i)(j)}\n"  )
        }
    }
    //TODO 1. 二维数组的声明
    def arrayDefine(): Unit ={
        var array = Array.ofDim[Int](3,3)
        array(0)(0) = 10 ;
        println("array(0)(0) = "+array(0)(0))
        println("array(0)(1) = "+array(0)(1))
     }

}
