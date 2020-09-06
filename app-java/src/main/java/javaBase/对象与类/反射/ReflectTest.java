package javaBase.对象与类.反射;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * TODO 1. 被大量地应用于 JavaBeans 中， 它是 Java 组件的体系结构
 * TODO 2. 能够分析类能力的程序称为反射（reflective )。
 * 特别是在设计或运行中添加新类时， 能够快速地应用开发工具动态地查询新添加类的能力。
 * TODO 3. 反射机制的功能极其强大，在下面可以看到， 反射机制可以用来：
 *       •在运行时分析类的能力。
 *       •在运行时查看对象， 例如， 编写一个 toString 方法供所有类使用。
 *       •实现通用的数组操作代码。
 *       •利用 Method 对象， 这个对象很像中的函数指针
 * TODO 4. 反射是一种功能强大且复杂的机制。 使用它的主要人员是工具构造者， 而不是应用程序员.
 */
public class ReflectTest {
    public static void main(String[] args) {
        String clasName ="java基础.对象与类.内部类.TalkingClock3$1TimePrinter" ;
        reflectionTest(clasName);

    }
    /**
     * TODO 5. 功能测试
     */
    @Test
    public static void reflectionTest(String clasName){
        // read class name from command line args or user input
        String classFullName=clasName;
        try
        {
            // print class name and superclass name (if != bject)
            Class cl = Class.forName(classFullName) ;
            Class supercl = cl.getSuperclass();

            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("classFullName="+ classFullName);
            if (supercl != null && supercl != Object.class) System.out.print(" extends "+ supercl.getName());

            System.out.print("\n{\n");
            printConstructors(cl);
            System.out.println();
            printMethods(cl);


            System.out.println();
            printFields(cl);
            System.out.println("}");
        }catch (Exception e ){
            e.printStackTrace();
        }
        System.exit(0) ;
    }



    public static void printConstructors(Class cl)
    {
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors)
        {
//            String name = c.getName() ;
            String name = c.getName() ;
            System.out.print(" ") ;
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");
            // print parameter types
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++)
            {
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getSimpleName());
            }
            System.out.println(");");
        }
    }

    public static void printMethods(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            Class retType = m.getReturnType();
            String name = m.getName();
            System.out.print(" ");
            // print modifiers.return type and method name
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + name + "(");

            Class<?>[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++) {
                if (j > 0) System.out.print(",");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(") ;");
        }
    }
    public static void printFields(Class cl)
    {
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields)
        {
            Class type = f.getType();
            String name = f.getName();
            System.out.print(" ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(type.getName() + " " + name +";");
        }
    }
}
