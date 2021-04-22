package javaBase.反射.domain;


import javaBase.反射.domain.annotations.AgeValidator;
import javaBase.反射.domain.annotations.Friend;

@Friend(name = "lisi",age = 23)
@Friend(name = "张三2",age = 32)
@AgeValidator
public class Person extends Human implements IHuman {

    //默认default修饰
    String name;

    //protected修饰
    protected String Person_name_protected ;

    //private修饰
    @AgeValidator(min = 1,max = 160)
    private int age;

    //public修饰
    public char sex='M';

    //无参构造
    public Person(){
        System.out.println("无参构造！！！");
    }
   //============================================
   //有参构造
    public Person(String name,int age,char sex){
        System.out.println("有参构造！！！");
        this.name=name;
        this.age=age;
        this.sex=sex;
    }
    //有参构造 私有的
    private Person(String name){
        System.out.println("有参构造！！！");
    }
    /**
     * Java权限有四个，分别为public,protected,默认,private，其开放程度依次降低
     *    public可供所有类访问 同类，同包，子类,不同包
     *    protected 同类，同包，子类可见
     *    default 同类，同包
     *    private 只能类本身内部的方法可以访问 同类
     *
     */
    //============================================================
    //反射机制获取类中的方法：Method: 对应类中的方法
    //现在给Person类添加一个private 方法、一个public 方法、一个defaut 方法、一个protected方法
    //private 方法
    private void method_private(){
        System.out.println("method_private");
    }
    private void method_private_2(String info){//private  带参数
        System.out.println("method_private_2:"+info);
    }
    //public 方法
    public void method_public(){
        System.out.println("method_public");
    }
    public void method_public_2(String name,int age,char sex){//public  带参数
        System.out.println("method_public_2");
        String info="Person{" +
                "name='" + name + '\'' +
                ", age=" + age  +
                ", sex='" + sex + '\'' +
                '}';
        System.out.println(info);
    }
    //protected 方法
    protected void method_protected(){
        System.out.println("method_protected");
    }
    protected void method_protected_2(String info){//protected  带参数
        System.out.println("method_protected_2:"+info);
    }
    //defaut 方法
    void method_default(){
        System.out.println("method_default");
    }
    void method_default_2(String info){//默认修饰符   带参数
        System.out.println("method_default_2:"+info);
    }
    //==========================================
    //如何读取父类或实现的接口中的方法
    @Override
    public void eat() {
        System.out.println("实现接口的方法：eat()无参：");
    }

    @Override
    public void eat(String info) {
        System.out.println("实现接口的方法：eat()有参："+info);
    }
    //===========================================
    //反射机制获取类中的注解：注解(Annotation)
    @AgeValidator
    public void setAge(@AgeValidator int age ){
        this.age = age ;
    }

}
