package scala类和对象

import scala.beans.BeanProperty

/**
  * scala中声明类的属性应该明确的初始化，否则发声错误
  * scala中如果属性没有访问修饰符，那么默认为公共权限，而且同时生成公共的get/set方法  字段名_$eq() 就是底层生成的set方法
  */
object ClassFieldTest {
    def main(args: Array[String]): Unit = {
        val emp = new Emp();
         println( emp.name)  //null
         println( emp.name2) //null
         println( emp.age)  //0

        //TODO 字段名_$eq() 就是底层生成的set方法
         emp.age_$eq(20)  //
         println( emp.age)  //0
         emp.age_=(40)  //  $eq 就是 =
        println( emp.age)  //0

    }
}

class Emp{

    //可以将null 赋值给引用对象：String, 也可以使用_ 进行引用对象值的初始化
    var name : String =null;
    var name2 :String = _ ;
    //不允许 将null 赋值给值对象：Int ,可以使用 _ 进行值对象的初始化
    //var age :Int = null ;
    var age :Int = _ ;


    /**
      * // 默认生成如下的get /set方法
      * public int age2() { return this.age2; }
      * public void age2_$eq(int x$1) { this.age2 = x$1; }
      *
      * //@BeanProperty会 使.class文件多生成如下的get 和 set方法
      * public void setAge2(int x$1) { this.age2 = x$1; }
      * public int getAge2() { return age2(); }
      */
    // 公共
    @BeanProperty
    var age2 : Int = _ ;


    // 私有  只有本类可以访问
    private var age3:Int =_ ;

    //同包的可以访问
    private[scala类和对象] var age4:Int =_ ;

    //只有本类和子类可以访问
    protected var age5 :Int = _ ;





}
