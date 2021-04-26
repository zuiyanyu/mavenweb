package javaBase.反射.reflect_class;

import javaBase.反射.domain.IHuman;
import javaBase.反射.domain.Person;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 *  * TODO 3 利用反射分析类的能力 - 检查类的结构
 *  * TODO 在 java.lang.reflect 包中有三个类 Field、 Method 和 Constructor 分别用于描述类的域、 方法和构造器。
 *  * TODO 这三个类都有一个叫做 getName 的方法， 用来返回项目的名称。
 *  *
 *  * TODO Held 类有一个 getType 方法， 用来返回描述域所属类型的 Class 对象。
 *  * TODO 1. Class类中的 getFields、 getMethods 和 getConstructors 方法将分别返回类提供的public 域、方法和构造器数组，其中包括超类的公有成员
 *  * TODO 2. Class 类的 getDeclareFields、getDeclareMethods 和 getDeclaredConstructors 方法将
 *  *          分别返回类中声明的全部域、 方法和构造器， 其中包括私有和受保护成员，但不包括超类的成员
 */

//TODO 反射获取类的基本信息
public class Class_baseInfo_reflect{
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * TODO 1. 反射机制获取类有三种方法
     */
    @Test
    public void GetClass() throws ClassNotFoundException {
        Class clazz = null;

        //1 直接通过类名.Class的方式得到
        clazz = Person.class;
        System.out.println("通过类名: " + clazz);

        //2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）
        Object obj = new Person();
        clazz = obj.getClass();
        System.out.println("通过getClass(): " + clazz);

        //3 通过全类名获取，用的比较多，但可能抛出ClassNotFoundException异常
        clazz = Class.forName("javaBase.反射.domain.Person");
        System.out.println("通过全类名获取: " + clazz);
    }


    /**
     * TODO 5.获取类的名称信息
     * 二进制名称表如下
     * Element Type	Encoding
     * boolean	Z
     * byte	B
     * char	C
     * class or interface	Lclassname
     * double	D
     * float	F
     * int	I
     * long	J
     * short	S
     */
    @Test
    public void getName(){
        //TODO 如果是一个实体类，则会返回完整包名路径名称,
        String name = clazz.getName();
        //name: javaBase.反射.domain.Person
        System.out.println(name);

        //TODO 如果是一个数组类型，则返回内部嵌套深度的一个或多个"["字符，后面拼接上基本数据类型的二进制名称
        int[] age = new int[]{2} ;
        String arrayName = age.getClass().getName();
        System.out.println(arrayName);//[I

        Person[] peoples = new Person[]{} ;
        System.out.println(peoples.getClass().getName());//[LjavaBase.反射.domain.Person;

    }
    @Test
    //TODO 返回源代码中给出的基础类的简单名称。 如果基础类是匿名的，则返回一个空字符串。
    public void getSimpleName(){
        String simpleName = clazz.getSimpleName();
        System.out.println("simpleName:"+simpleName);

        IHuman iHuman = new IHuman() {
            @Override
            public void eat() {

            }

            @Override
            public void eat(String info) {

            }
        };
        String simpleName1 = iHuman.getClass().getSimpleName();
        System.out.println("simpleName1:"+simpleName1);

    }


    //TODO 6.获取类所在的包名
    @Test
    public void getPackage(){
        Package aPackage = clazz.getPackage();
        //aPackage = package javaBase.反射.domain
        System.out.println("aPackage = "+aPackage);
    }

    //TODO 获取类的父类
    public void a(){


    }
    //TODO 获取类的接口

    //TODO 获取类的泛型信息
    @Test
    public void getGenericSuperclass(){
        Type genericSuperclass = clazz.getGenericSuperclass();

    }

    // TODO 2.Class类的newInstance()方法，创建类的一个对象。
    @Test
    public void testNewInstance() throws IllegalAccessException, InstantiationException {
        //使用Class类的newInstance()方法使用类的无参构造方法一个对象。
        Object obj = clazz.newInstance();
        System.out.println(obj);
    }








}
