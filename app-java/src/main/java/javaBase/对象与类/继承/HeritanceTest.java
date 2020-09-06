package javaBase.对象与类.继承;

import org.junit.Test;

@SuppressWarnings("unchecked")
public class HeritanceTest {

    @Test
    public void testHeritance(){

        A a = new B();
//        ((B) a).b();
//        A[] aa = new A[1] ;
//        aa[0] = new B();
//        ((B) aa[0]).b();
//
//        a.c();
//        B b = new B();
//        b.c(new B());
//        System.out.println(a.name);
//        System.out.println( ((B) a).name);
//        Class<? extends A> aClass = a.getClass();
//        System.out.println(aClass);
//
//        Objects.equals(a,b);
//
//        Object num = 24 ;
//        int i = num.hashCode();
//        System.out.println(i);

        System.out.println(a);

        A a2 = new A();
        System.out.println(a2);


    }
}

class A {
    protected String name = "张三";
    public void a(){
        System.out.println("parent");
    }
    public void c(){
        System.out.println("c - parent");
    }

    @Override
    public String toString() {
        System.out.println("=================");
        System.out.println("parent this ="+this.getClass());
        System.out.println("parent supperClass ="+this.getClass().getSuperclass());
        System.out.println("parent className ="+this.getClass().getName());
        return getClass().getName()+"{" +
                "name='" + name + '\'' +
                '}';
    }
}
class B extends  A{
    public void b(){
        System.out.println("Son");
    }
    @Override
    public final void c(){
        System.out.println("c - son："+name);
    }
    public final void c(A a){
        System.out.println("c - son："+a.name);
    }
    @Override
    public String toString() {
        System.out.println("son this ="+this.getClass());
        return super.toString()+"";
    }
}