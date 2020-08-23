package java基础.对象与类.反射;

import java基础.domain.Employee;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
/**
 * TODO 3 利用反射分析类的能力 - 检查类的结构
 * TODO 在 java.lang.reflect 包中有三个类 Field、 Method 和 Constructor 分别用于描述类的域、 方法和构造器。
 * TODO 这三个类都有一个叫做 getName 的方法， 用来返回项目的名称。
 *
 * TODO Held 类有一个 getType 方法， 用来返回描述域所属类型的 Class 对象。
 * TODO 1. Class类中的 getFields、 getMethods 和 getConstructors 方法将分别返回类提供的public 域、方法和构造器数组，其中包括超类的公有成员
 * TODO 2. Class 类的 getDeclareFields、getDeclareMethods 和 getDeclaredConstructors 方法将
 *          分别返回类中声明的全部域、 方法和构造器， 其中包括私有和受保护成员，但不包括超类的成员
 */
public class FieldTest {
    @Test
    public void FieldTest() throws IllegalAccessException, InstantiationException, NoSuchFieldException, ClassNotFoundException {
        Class<?> aClass = Class.forName("java基础.domain.Employee");

        //TODO 获取 public 权限的属性
        Field[] fields = aClass.getFields();
        System.out.println("fields="+ Arrays.toString(fields));
        for (Field field : fields) {
            System.out.println("====================");
            System.out.println("field="+field.toString());//field=public java.lang.String java基础.domain.Employee.school
            System.out.println(""+field.getName());//school
            Class<?> type = field.getType();
            System.out.println("type.toString()="+type);//type.toString()=class java.lang.String
            System.out.println("type.getName()="+type.getName());//type.getName()=java.lang.String
            System.out.println("type.getSimpleName()="+type.getSimpleName());//type.getSimpleName()=String
        }

        //TODO  获取所有被声明的字段属性 。 （包括private 权限的字段属性）
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("====================");
            System.out.println(""+field.getName());//
            Class<?> type = field.getType();
            System.out.println("type.getSimpleName()="+type.getSimpleName());//type.getSimpleName()=String
        }

        Object o = aClass.newInstance();
        Employee e = (Employee) o;
        System.out.println("age="+e.getAge());

        //获取一个指定私有属性，并为属性直接设置值
        Field age2 = aClass.getDeclaredField("age");
        //私有属性需要打开访问权限
        age2.setAccessible(true);
        age2.set(e,20);
        System.out.println("age="+e.getAge());
        age2.setAccessible(false);

        //TODO 获取修饰符
        int modifiers = age2.getModifiers();
        String s = Modifier.toString(modifiers);
        System.out.println("modifiers="+s);

        Employee e2 = new Employee("测试",110) ;
        //获取指定对象实例的 字段值
        age2.setAccessible(true);
        Object age_e2 = age2.get(e2);
        System.out.println("age_e2 ="+ age_e2);//age_e2 =110
        age2.setAccessible(false);

    }
}
