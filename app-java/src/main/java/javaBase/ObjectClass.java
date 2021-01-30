package javaBase;

/**
 * 关于Object的继承问题：
 * 1. java是单继承的体系，Object是所有类的父类
 * 2. 问题： 当类B继承了类A，那么Object还是类B的直接父类吗？
 * 答案：不是的。
 * 会变成这样一种形式：
 * A extends Object {}
 * B extends A{}
 *
 * Object变成了B的间接继承类。
 *
 * 3. 验证：
 * 开始的时候，A,B都各自继承Object, A,B的方法表都
 */
public class ObjectClass {
    public static void main(String[] args) {

       A a = new B() ;
         /*1. 输出结果是 javaBase.B= a9 ;info= B，可以分析出来：
              1.1  a的实际类型是B，但是实际执行的方法却是A类的toString()方法。
              1.2  执行toString()方法的时候，
                   this代表的是javaBase.B，
                   并且this.info()执行的是B实例的info()方法，
                   但是this.i却是 A类的i

           2. TODO 问题1：虚拟机是如何通过B实例找到A类的toString()方法执行的呢？
              TODO 问题2：this既然代表了B实例，为何不能通过this来访问B实例的字段，却能访问B实例的方法。
           */

        // B 没有自己的方法，B类方法表中的所有的方法都指向类A中的方法
        //类A的方法表中，只有一个方法toString(),所以
        System.out.println(a);
    }

}
class A{
    public int i =9 ;
    //
    @Override
    public String toString(){
        Object obj = this ;
        return this.getClass().getName() +"= a" + this.i + " ;info= " + this.info();
    }
    public String info(){
        return "A";
    }
}


class B extends A{
    public int i = 20 ;
    public String info(){
        return "B";
    }
}
