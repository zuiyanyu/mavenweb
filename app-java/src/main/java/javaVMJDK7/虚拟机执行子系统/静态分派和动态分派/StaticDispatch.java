package javaVMJDK7.虚拟机执行子系统.静态分派和动态分派;

/**
 * 方法静态分派演示
 * 依赖静态类型来定位方法执行版本的分派动作称为 静态分派。
 * 静态分派的典型应用就是方法重载。 静态分派发生在编译期，因此确定静态分派的动作实际上不是虚拟机来执行的
 */
public class StaticDispatch {
    static abstract class Human{
    }
    static class Man extends Human{
    }
    static class Woman extends Human{
    }
    public void sayHello( Human guy) {
        System.out.println(  "hello,guy ");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman");
    }
    public void sayHello(Woman guy){
        System.out.println("hello,lady ");
    }
    public static void main(String[]args) {
        //TODO 1.代码中Human 称为变量的静态类型，或者叫做外观类型。
        //TODO 2.后面的Man或者Woman则称为变量的实际类型
        //TODO 3.静态类型和实际类型在程序中都可以发生一些变化，区别是静态类型的变化仅仅在使用时候发生。变量本省的静态类型不会被改变
        //TODO   并且，最终的静态类型是在编译期可知的；
        //TODO 4.实际类型变化的结果是在运行期才可确定，编译器在编译程序的时候并不知道一个对象的实际类型是什么。


        Human man=new Man();
        Human woman=new Woman();

        StaticDispatch sr=new StaticDispatch();

        //TODO 方法静态分派，在编译期确定调用目标：
        //TODO 在方法接收者sayHello()已经确定是对象sr的前提下，使用哪个重载版本，就完全取决于传入参数的数量和数据类型。
        //TODO 虚拟机(确切说是编译器)在重载时候是通过参数的静态类型而不是实际类型作为判定依据的。并且静态类型是编译期可知的。
        //TODO 所以Javac编译期会根据参数的静态类型决定使用哪个重载版本，所以选择sayHello(Human)作为调用目标
        sr.sayHello(man);   //hello,guy
        sr.sayHello(woman); //hello,guy

        //TODO 实际类型变化
        man = new Woman();

        //TODO 静态类型变化
        sr.sayHello((Man) man); //异常
        sr.sayHello((Woman) woman); //hello,lady
    }}
