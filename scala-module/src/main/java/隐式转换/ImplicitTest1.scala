package 隐式转换
package dsgdg{

    /**
      * scala 允许开发者自定义类型转换规则
      * 当sala编译器在编译时候发现类型错误，会尝试从开发者的位置查找转换规则进行二次编译
      *
      * 隐士转换规则不仅仅是可以转换类型，还可以扩展功能
      */
    object ImplicitTest1 {
        def main(args: Array[String]): Unit = {
            //进行隐士转换，将Short类型隐士转换为Byte类型
            implicit def shortToByte(int:Short):Byte ={
                int.toByte
            }

            var b:Byte = 8 ;
            var s:Short = b ;
            b =s ; // Int类型转换为byte类型会报错，
            println(b)

          // ========================================================
         //必须先定义后使用，即要在调用语句前声明
          implicit def parentToSon(parent:Parent):Son={
              if(parent.isInstanceOf[Son]){
                  var son:Son  = parent.asInstanceOf[Son]
                  son
              }else {
                  new Son() ;
              }
          }
            val parent:Parent = new Son();
//            if(parent.isInstanceOf[Son]){
//                var son:Son  = parent.asInstanceOf[Son]
//                son.getName()
//            }
            //替换上方的代码，这里我们也可以对类使用隐士转换
             val son:Son = parent;
             son.getName();


        }

    }

    class Parent{
        def getName(): Unit ={
            println("parent ...");
        }
    }
    class Son  extends Parent {
         override def getName(): Unit ={
            println("son ...");
        }
    }


}