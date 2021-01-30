package scala类和对象04

object 类的扩展点11 {
    def main(args: Array[String]): Unit = {
        case class Student();
        //TODO 1.获取类的Class对象
        val clazz:Class[_] = Student.getClass

        //TODO 2. classOf[T] == T.class
        val studentClass:Class[Student] = classOf[Student] ;

        //TODO 3.类的类型判断
        //  Student.isInstanceOf[AnyRef]   ==  java中 ： Student instanceOf  Object
        val bool: Boolean = Student.isInstanceOf[AnyRef]

        //TODO 4.类的向上向下转型
        //Student.asInstanceOf[AnyRef]   == java中的 ：（Object）Student
        val unit: AnyRef = Student.asInstanceOf[AnyRef]

        //TODO 5.给类定义别名  类名过长的时候，可以使用类别名代替类名
        type stu = Student
        val student:Student = new stu() ;
    }
}
