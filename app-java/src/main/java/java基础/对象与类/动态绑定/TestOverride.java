package java基础.对象与类.动态绑定;

public class TestOverride {
    public static void main(String[] args) {
        // TODO 多态的动态绑定： 利用的是虚方法表
        // TODO 动态绑定：调用对象的成员方法时，JVM会将对象的实际内存和当前的方法进行绑定。 即:
        // TODO 成员变量没有动态绑定操作，成员变量的调用是在哪里声明就在哪里使用。
// TODO 1. 执行方法的时候，每次都要先从对象实际内存中查找，实际内存没有就向父级内存中查找；
// TODO 2. 方法运行时候就根据方法体所在内存中直接查找，而不用先查子类再查父类。
        test01();
        test02();
        test03();

     }


    public static void test01(){
        //多态：
        TestOverride_A a = new TestOverride_B();
        System.out.println(a.getRestult());  //40
    }
    public static void test02(){
        //多态：
        TestOverride_A a = new TestOverride_C();
        System.out.println(a.getRestult()); // 30
    }

    public static void test03(){
        //多态：
        TestOverride_A a = new TestOverride_D();
        System.out.println(a.getRestult2()); //40
    }
}

class TestOverride_A{
    public int i = 10 ;
    public int getRestult(){
        return  i + 20 ;
       //实际完整写法为 return  this.i + 20 ;  this只是在本类中可以省略。
    }
    public int getRestult2(){
        return  getI() + 20;
    }
    public int getI(){
        return i ;
    }
}

class TestOverride_B extends  TestOverride_A{
    public int i = 20 ;
    public int getRestult(){
        return  i + 20 ;
    }
}

class TestOverride_C extends  TestOverride_A{
    public int i = 20 ;
//    public int getRestult(){
//        return  i + 20 ;
//    }
}


class TestOverride_D extends TestOverride_A{
    public int i = 20 ;

    public int getI(){
        return i ;
    }
}