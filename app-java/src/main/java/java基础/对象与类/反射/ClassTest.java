package java基础.对象与类.反射;

import java基础.domain.Employee;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**TODO    Class 类
 * TODO 1. 在程序运行期间，Java 运行时系统始终为所有的对象维护一个被称为运行时的类型标识。
 * TODO 2. 类型标识这个信息跟踪着每个对象所属的类。 虚拟机利用运行时类型信息选择相应的方法执行。
 * TODO 3. 然而， 可以通过专门的 Java 类访问这些信息。保存这些信息的类被称为 Class
 * TODO 4. Object 类中的 getClass( ) 方法将会返回一个 Class 类型的实例
 */
public class ClassTest {

    /**
     * TODO 1. 三种获取Class 实例的方式
     * 如同用一个 Employee 对象表示一个特定的雇员属性一样， 一个 Class 对象将表示一个特
     * 定类的属性。
     */
    @Test
    public void getClassTyep() throws ClassNotFoundException {
        Employee e = new Employee();
        //TODO 1. 方式1   Object.getClass()方式
        Class<? extends Employee> aClass = e.getClass();
        //最常用的 Class 方法是 getName。 这个方法将返回类的全限定名字
        System.out.println("className ="+aClass.getName());//className =java基础.domain.Employee

        //TODO 2 . 方式2  类.class
        Class<Employee> employeeClass = Employee.class;
        System.out.println("className ="+employeeClass.getName());//className =java基础.domain.Employee

        //TODO 3. 方式3  Class.forName
        Class<?> aClass1 = Class.forName("java基础.domain.Employee");
        System.out.println("className ="+aClass1.getName());//className =java基础.domain.Employee

    }
    /**
     * TODO 2. 使用Class 类实例获取 类实例对象
     */
    @Test
    public void classInstance() throws Exception{
        Class<?> aClass1 = Class.forName("java基础.domain.Employee");


        //TODO 方式1 使用无参构造方法创建实例 。newlnstance 方法调用默认的构造器
        //创建了一个与 e 具有相同类类型的实例。 newlnstance 方法调用默认的构造器 （没有参数的构造器）初始化新创建的对象。 如果这个类没有默认的构造器， 就会抛出一个异常_
        Object o = aClass1.newInstance();
        Employee e = null ;
        if(o instanceof  Employee) {
            e = (Employee)o;
            String name = e.getName();
            System.out.println("name = "+name);//name = 张三
        }


        //TODO 方式2 使用有参构造方法  必须使用 Constructor 类中的 newlnstance 方法
        Constructor<?> constructor = aClass1.getConstructor(String.class);
        Object o2 = constructor.newInstance("李四");
        if(o instanceof  Employee) {
            e = (Employee)o2;
            String name = e.getName();
            System.out.println("name = "+name);//name = 李四
        }
    }

}
