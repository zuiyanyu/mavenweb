package javaBase.泛型;

import javaBase.domain.GenericReflect;
import javaBase.domain.Pair;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * 反射允许你在运行时分析任意的对象。 如果对象是泛型类的实例，关于泛型类型参数则得不到太多信息， 因为它们会被擦除。
 *TODO ======================================================================
 * TODO 1.泛型 Class 类
 * 现在， Class类是泛型的。 例如， String.class 实际上是一个：：Class<String> 类的对象（事实上，是唯一的对象）。
 * Employee.class 是类型 Class<Employee> 的一个对象。
 *
 * 类型参数十分有用， 这是因为它允许 ClaSS<T> 方法的返回类型更加具有针对性。
 * 下面Class<T> 中的方法就使用了类型参数：
 *      T newInstance()
 *      T cast(Object obj)
 *      T[] getEnumConstants()
 *      Class<? super T> getSuperclass()
 *      Constructors getConstructor(C1ass... parameterTypes)
 *      Constructors getDeclaredConstructor (Class... parameterTypes)
 *
 * newlnstance 方法返回一个实例，这个实例所属的类由默认的构造器获得。它的返回类型
 * 目前被声明为 T， 其类型与 Class<T> 描述的类相同，这样就免除了类型转换。
 * 如果给定的类型确实是 T 的一个子类型，cast 方法就会返回一个现在声明为类型 T 的对
 * 象， 否则，抛出一个 BadCastException 异常。
 * 如果这个类不是 enum 类或类型 T 的枚举值的数组， getEnumConstants 方法将返回 null。
 * 最后， getConstructor 与 getdeclaredConstructor 方 法 返 回 一 个 Constructor<T> 对象。
 * Constructor 类也已经变成泛型， 以便 newlnstance 方法有一个正确的返回类型。
 *
 *
 * TODO java.lang.Class<T>
 * • T newInstance()
 * 返回无参数构造器构造的一个新实例。
 * • T cast(Object obj)
 * 如果 obj 为 null 或有可能转换成类型 T， 则 返 回 obj ; 否 则 拋 出 BadCastException
 * 异常。
 * • T[ ] getEnumConstants( ) 5.0
 * 如果 T 是枚举类型， 则返回所有值组成的数组，否则返回 mill。
 * • Class<? super T> getSuperclass( )
 * 返回这个类的超类。如果 T 不是一个类或 Object 类， 则返回 null。
 * • Constructor<T> getConstructor(Class. • • parameterTypes) 1.1
 * • Constructor<T> getDeclaredConstructor(Class. • . parameterTypes) 1.1
 * 获得公有的构造器， 或带有给定参数类型的构造器。
 *
 * TODO java.lang.reflect.Constructor<T>
 *     • T newlnstance(0bject . . . parameters)
 *      返回用指定参数构造的新实例。
 *
 *
 * TODO ======================================================================
 * TODO 2. 使用 Class<T> 参数进行类型匹配
 *有时， 匹配泛型方法中的 Class<I> 参数的类型变量很有实用价值。下面是一 标准的示例：
 * public static <T> Pair<T> makePair(Class<T> c) throws InstantiationException,IllegalAccessException
 * {
 *      return new Pair<>( c.newInstance(),c.newInstance());
 * }
 * 如果调用
 *     makePair(Employee.class)
 * Employee.class 是类型 Class<Employee> 的一个对象。makePair 方法的类型参数 T 同 Employee
 * 匹配， 并且编译器可以推断出这个方法将返回一个 Pair<Employee>。
 *
 * TODO ======================================================================
 * TODO 3.虚拟机中的泛型类型信息
 *
 * Java 泛型的卓越特性之一是在虚拟机中泛型类型的擦除。令人感到奇怪的是， 擦除的类仍然保留一些泛型祖先的微弱记忆。
 * 例如， 原始的 Pair 类知道源于泛型类 Pair<T>
 *
 * 类似地， 看一下方法
 * public static Comparable min(Coniparable[] a)
 * 这是一个泛型方法的擦除
 * public static <T extends Comparable<? super T>> T min(T[] a)
 *TODO 可以使用反射 API 来确定：
 *      •这个泛型方法有一个叫做 T 的类型参数。
 *TODO •这个类型参数有一个子类型限定， 其自身又是一个泛型类型。
 *      •这个限定类型有一个通配符参数。
 *TODO •这个通配符参数有一个超类型限定。
 *      •这个泛型方法有一个泛型数组参数。
 * 换句话说，需要重新构造实现者声明的泛型类以及方法中的所有内容。但是，不会知道
 * 对于特定的对象或方法调用， 如何解释类型参数
 *
 * TODO java.lang.reflect.Type
 * 为了表达泛型类型声明， 使用java.lang.reflect 包中提供的接口 Type。这个接口包含下列子类型：
 *      •Class 类，描述具体类型。
 *      •TypeVariable 接口，描述类型变量（如 T extends Comparable<? super T>)
 *      •WildcardType 接口， 描述通配符 （如 ？super T )。
 *      •ParameterizedType 接口， 描述泛型类或接口类型（如 Comparable<? super T>)。
 *      •GenericArrayType 接口， 描述泛型数组（如 T[ ]。)
 * TODO Java.lang.Class<T>
 *      • TypeVariable[] getTypeParameters( ) 5.0
 *          如果这个类型被声明为泛型类型， 则获得泛型类型变量，否则获得一个长度为 0 的数组。
 *      • Type getGenericSuperclass( ) 5.0
 *          获得被声明为这一类型的超类的泛型类型； 如果这个类型是 Object 或不是一个类类型(class type), 则返回 null。
 *      •Type[] getGenericInterfaces( ) 5.0
 *          获得被声明为这个类型的接口的泛型类型（以声明的次序，) 否则， 如果这个类型没有实现接口，返回长度为 0 的数组。
 *
 * TODO java.lang.reflect.Method
 *      • TypeVariable[] getTypeParameters( ) 5.0
 *          如果这个方法被声明为泛型方法， 则获得泛型类型变量，否则返回长度为 0 的数组。
 *      •Type getGenericReturnType( ) 5.0
 *          获得这个方法被声明的泛型返回类型。
 *      •Type[ ] getGenericParameterTypes( ) 5.0
 *          获得这个方法被声明的泛型参数类型。 如果这个方法没有参数， 返回长度为 0 的数组。
 *TODO java.lang.reflect.TypeVariable 5.0
 * •TypeVariable 接口，描述类型变量（如 T extends Comparable<? super T>)
 *      •String getName( )
 *          获得类型变量的名字。
 *      •Type[] getBounds( )
 *          获得类型变量的子类限定，否则， 如果该变量无限定， 则返回长度为 0 的数组
 * TODO java.Iang.reflect.WildcardType 5.0
 *  •WildcardType 接口， 描述通配符 （如？super T )。
 *      • Type[ ] getUpperBounds( )
 *           获得这个类型变量的子类 ( extends) 限定，否则， 如果没有子类限定，则返回长度为 0 的数组。
 *      • Type[ ] getLowerBounds( )
 *          获得这个类型变量的超类（super) 限定，否则， 如果没有超类限定， 则返回长度为 0的数组。
 *TODO java.Iang.reflect.ParameterizedType 5.0
 *  •ParameterizedType 接口， 描述泛型类或接口类型（如 Comparable<? super T>)。
 *      • Type getRawType( )
 *          获得这个参数化类型的原始类型。
 *      •Type[ ] getActualTypeArguments( )
 *           获得这个参数化类型声明时所使用的类型参数。
 *      • Type getOwnerType( )
 *          如果是内部类型， 则返回其外部类型， 如果是一个顶级类型， 则返回 null。
 * TODO java.Iang.reflect.GenericAnrayType 5.0
 * •GenericArrayType 接口， 描述泛型数组（如 T[ ]。)
 *      •Type getGenericComponentType( )
 *          获得声明该数组类型的泛型组件类型。
 */
public class 反射和泛型 {
    public static void main(String[] args) {
        ClassTest();
    }
    /* TODO java.lang.reflect.Type
    * 为了表达泛型类型声明， 使用java.lang.reflect 包中提供的接口 Type。这个接口包含下列子类型：
    *      •Class 类，描述具体类型。
    *      •TypeVariable 接口，描述类型变量（如 T extends Comparable<? super T>)
    *      •WildcardType 接口， 描述通配符 （如？super T )。
    *      •ParameterizedType 接口， 描述泛型类或接口类型（如 Comparable<? super T>)。
    *      •GenericArrayType 接口， 描述泛型数组（如 T[ ]。)
    */

    public static void TypeTest(Type type,String msg){
        String typeName = type.getTypeName();
        System.out.println(msg+"[typeName]="+typeName);
    }
    /* TODO Java.lang.Class<T>
    *      • TypeVariable[] getTypeParameters( ) 5.0
    *          如果这个类型被声明为泛型类型， 则获得泛型类型变量，否则获得一个长度为 0 的数组。
    *      • Type getGenericSuperclass( ) 5.0
    *          获得被声明为这一类型的超类的泛型类型； 如果这个类型是 Object 或不是一个类类型(class type), 则返回 null。
    *      •Type[] getGenericInterfaces( ) 5.0
    *          获得被声明为这个类型的接口的泛型类型（以声明的次序，) 否则， 如果这个类型没有实现接口，返回长度为 0 的数组。
    */

    public static void ClassTest(){
        System.out.println("===========ClassTest==============");
        Class<GenericReflect> genericReflectClass = GenericReflect.class;

        TypeVariable<Class<GenericReflect>>[] typeParameters = genericReflectClass.getTypeParameters();
        for (TypeVariable<Class<GenericReflect>> typeParameter : typeParameters) {
            //<R extends Comparable<? super R>&Serializable>
            TypeVariableTest(typeParameter);
            //getName =R
            //typeName =java.lang.Comparable<? super R>
            //typeName =java.io.Serializable
        }

        Type genericSuperclass = genericReflectClass.getGenericSuperclass();
        TypeTest(genericSuperclass,"genericSuperclass");
        //genericSuperclass[typeName]=javaBase.domain.Pair<R>


        Type[] genericInterfaces = genericReflectClass.getGenericInterfaces();
        System.out.println("genericInterfaces.length = "+genericInterfaces.length); // genericInterfaces.length = 2
        for (Type genericInterface : genericInterfaces) {
            TypeTest(genericInterface,"genericInterface");
            //genericInterface[typeName]=java.io.Serializable
            //genericInterface[typeName]=javaBase.domain.GenericReflectFlag<R>
        }
    }

    /* TODO java.lang.reflect.Method
    *      • TypeVariable[] getTypeParameters( ) 5.0
    *          如果这个方法被声明为泛型方法， 则获得泛型类型变量，否则返回长度为 0 的数组。
    *      •Type getGenericReturnType( ) 5.0
    *          获得这个方法被声明的泛型返回类型。
    *      •Type[ ] getGenericParameterTypes( ) 5.0
    *          获得这个方法被声明的泛型参数类型。 如果这个方法没有参数， 返回长度为 0 的数组。
    */

    public static  void MethodTest(Method  t){
    }
    /*TODO java.lang.reflect.TypeVariable 5.0
    * •TypeVariable 接口，描述类型变量（如 T extends Comparable<? super T>)
    *      •String getName( )
    *          获得类型变量的名字。
    *      •Type[] getBounds( )
    *          获得类型变量的子类限定，否则， 如果该变量无限定， 则返回长度为 0 的数组
    */
    public static <T> void TypeVariableTest(TypeVariable<Class<T>> typeParameter){
        System.out.println("===========TypeVariable==============");
        //<R extends Comparable<? super R>&Serializable>
        String getName = typeParameter.getName(); // R
        System.out.println("getName ="+getName);

        Type[] bounds = typeParameter.getBounds();
        for (Type bound : bounds) {
            String typeName = bound.getTypeName();
            System.out.println("typeName ="+typeName); // java.lang.Comparable<? super R> ,java.io.Serializable
        }



    }
    /* TODO java.Iang.reflect.WildcardType 5.0
    *  •WildcardType 接口， 描述通配符 （如？super T )。
    *      • Type[ ] getUpperBounds( )
    *           获得这个类型变量的子类 ( extends) 限定，否则， 如果没有子类限定，则返回长度为 0 的数组。
    *      • Type[ ] getLowerBounds( )
    *          获得这个类型变量的超类（super) 限定，否则， 如果没有超类限定， 则返回长度为 0的数组。
    */

    public  static void WildcardTypeTest(){
    }

    /*TODO java.Iang.reflect.ParameterizedType 5.0
    *  •ParameterizedType 接口， 描述泛型类或接口类型（如 Comparable<? super T>)。
    *      • Type getRawType( )
    *          获得这个参数化类型的原始类型。
    *      •Type[ ] getActualTypeArguments( )
    *           获得这个参数化类型声明时所使用的类型参数。
    *      • Type getOwnerType( )
    *          如果是内部类型， 则返回其外部类型， 如果是一个顶级类型， 则返回 null。
    */
    @Test
    public   void ParameterizedTypeTest() {
        class AA<R> extends Pair<R> {}
        ParameterizedTypeTest(new AA<String>());

        System.out.println("===========================");

        class BB extends Pair<String> {}
        ParameterizedTypeTest(new BB());

    }

    /**运行结果
     * genericSuperclass.getTypeName =javaBase.domain.Pair<R>
     * genericSuperclass is ParameterizedType
     * RtypeName = R
     * ===========================
     * genericSuperclass.getTypeName =javaBase.domain.Pair<java.lang.String>
     * genericSuperclass is ParameterizedType
     * RtypeName = java.lang.String
     * ------rType is a Class type -----
     * pairParamClass = java.lang.String
     * @param pair
     * @param <T>
     */
    public <T>  void ParameterizedTypeTest(Pair<T> pair){
         // --------------------------
        Class<? extends Pair> aClass = pair.getClass();

        //获取父类
        Type genericSuperclass = aClass.getGenericSuperclass();
        System.out.println("genericSuperclass.getTypeName ="+genericSuperclass.getTypeName());// genericSuperclass.getTypeName =javaBase.domain.Pair<R>

        //判断继承的父类是不是泛型类
        if(genericSuperclass instanceof ParameterizedType){
            System.out.println("genericSuperclass is ParameterizedType");
            //向下转型
            ParameterizedType parmType = (ParameterizedType)genericSuperclass ;

            //获得这个参数化类型声明时所使用的类型参数。
            Type[] actualTypeArguments = parmType.getActualTypeArguments();

            //我们继承的 Pair<R> 只有一个泛型类型 R ，故
            Type rType = actualTypeArguments[0];
            String RtypeName = rType.getTypeName();
            System.out.println("RtypeName = "+ RtypeName);// RtypeName = R

            // 如果R给定的是某个具体的类，就可以进行获取这个具体类 对应的Class类
            if(rType instanceof Class){
                System.out.println("------rType is a Class type -----");
                Class<T> pairParamClass =(Class<T>)rType ;
                System.out.println("pairParamClass = "+pairParamClass.getName());
            }


//            System.out.println(pairParamClass.getName());
        }
    }
    /* TODO java.Iang.reflect.GenericAnrayType 5.0
    * •GenericArrayType 接口， 描述泛型数组（如 T[ ]。)
    *      •Type getGenericComponentType( )
    *          获得声明该数组类型的泛型组件类型。
    */

    public static void GenericAnrayTypeTest(){
    }
}