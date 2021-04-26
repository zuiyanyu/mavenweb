package javaBase.泛型;

import java.util.ArrayList;
import java.util.List;

public class SupperAndExtends {
    public static void main(String[] args) {
        //  List<? extends Son>  中问号的含义：?表示一个类型范围，list集合中的任何一个元素类型都要小于Son这个类型范围。
        //  那么就表示，List<? extends Son>中任何一个元素都必须是 Son的子类,但是不确定子类的具体类型.
        //  如果你向 List<? extends Son>中添加元素，但是编译的时候用的是一个CAD#1代替的,就不知道你添加的类型是否在这个范围了。
        List<? extends Son> list1 = new ArrayList<>();
        // List<? extends Son>  中问号的含义：?是list的元素类型， ，表示list集合中的任何一个元素的类型都是?类，?类是Son的子类。
        List<? super Person> list2 = new ArrayList<>();

        Person person = new Person();
        Son son = new Son();

        //读数据
        Son son1 = list1.get(0);
        Object son2 = list1.get(0);

        Object object = list2.get(0);

        //list1  写数据
//        list1.add(son);
//        list1.add(person);
        //list1.add(new Object());
        list1.add(null);


        //list2 写数据
        list2.add(son);
//        list2.add(new Object());
//        list2.add(Person);

        List<Son> list3 = new ArrayList<>();
        List<Person> list4 = new ArrayList<>();

//        list1 = list4 ;
        //方法接收数据
        extendsTest01(son);
//        extendsTest01(person);

        list1 = list3 ;

        //TODO Son extends Person
        List<? extends Son> list5 = new ArrayList<>();

        List<? super Son> list6 = new ArrayList<>();
        List<? super Person> list7 = new ArrayList<>();

        List<Person> list8 = new ArrayList<>();
        List<Object> list9 = new ArrayList<>() ;
        List list10 = new ArrayList() ;


        list6 = list7;//ok
       // list7 = list6; // 异常

//        list6 = list5 ; // 异常
//        list7 = list5; // 异常
//        list8 = list5; // 异常
//        list9 = list5; // 异常
//        list10 = list5 ; // ok


        list5 = list3 ;
        //list3 = list5;
        extendsTest02(list1);
        extendsTest02(list5);
    }

    //这里T代表一个确定的类型，是Son的子类，可以别推断出一个具体类
    public static  <T extends Son>  void extendsTest01(T  a){}
    public static   void extendsTest02(List<? extends Son>  a){}
}

//泛型集合类型的测试

class Person{}
class Son extends  Person{}


