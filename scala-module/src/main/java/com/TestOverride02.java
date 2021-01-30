package com;

public class TestOverride02 {
    public static void main(String[] args) {
        // ===============TODO 执行方法：执行方法的时候，每次都要先从对象实际内存中查找===========================
        // TODO 多态的动态绑定： 利用的是虚方法表
        // TODO 动态绑定：调用对象的成员方法时，JVM会将对象的实际内存和当前的方法进行绑定。 即:
        // TODO 成员变量没有动态绑定操作，成员变量的调用是在哪里声明就在哪里使用。
        // TODO 1. 执行方法的时候，每次都要先从对象实际内存中查找，实际内存没有就向父级内存中查找；
        // TODO 2. 方法运行时候就根据方法体所在内存中直接查找，而不用先查子类再查父类。

        //TODO 动态绑定：调用对象的成员方法时候，JVM会将对象的实际内存和当前的方法进行绑定。
        //TODO 多态中，成员变量可以被继承，但是不会被动态绑定。
        A a = new B();
        System.out.println(a.age); // 30 执行字段调用，直接从父类A内存中查找。

        //TODO 在调用 getResult()的时候，会将 com.B实例给传递到getResult()方法，getResult()方法使用银行的this进行接收。
        // 实际调用形式： getResult(Objct this)   ->  a.getResult(a)
        /**
         * 执行步骤如下：
         */
        System.out.println(a.getResult()); //20
        /**
         * 运行结果：
         * this.class=com.B
         * A.this.class=com.B
         * 子类
         * this.class=com.B
         * B.this.class=com.B
         * -----------------------
         * 20
         */
    }
}

/**
 *
 */
class A {
    public int age = 30 ;
    public int i = 10;
    //TODO com.B 执行父类的getResult()方法的同时，会将getResult()绑定到这个方法
    //TODO 所以，com.B实际是在com.A的内存环境中执行的getResult()，而非com.B自己的内存环境中执行的。

    public int getResult() {
        System.out.println("this.class="+this.getClass().getName());
        System.out.println("A.this.class="+A.this.getClass().getName());
        //TODO this是对象 com.B ，所以 this.intfo() 是在调用 com.B 的intfo()方法，如果com.B 没有这个方法，才会到父类中找这个方法
        this.intfo();
        return this.i + 10;
    }
    public void intfo(){
        System.out.println("父类");
        System.out.println("this.class="+this.getClass().getName());
        System.out.println("A.this.class="+A.this.getClass().getName());
        System.out.println("-------------------");
    }
}
class B extends A {
    public int age = 1000 ;

    public int i = 20;
//    public int getResult() {
//        return i + 20;
//    }
    public void intfo(){
        System.out.println("子类");
        System.out.println("this.class="+this.getClass().getName());
        System.out.println("B.this.class="+B.this.getClass().getName());
        System.out.println("-----------------------");

    }
}
