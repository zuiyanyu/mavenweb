package 泛型;

import java.util.ArrayList;
import java.util.List;

public class 注意点 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        attention02();
    }
    //TODO 注意点2 泛型可以对类型进行约束。比如：对 方法返回值类型，方法入参类型 做类型约束
    public static void attention02() throws InstantiationException, IllegalAccessException {
       // 这几行代码是正常的代码逻辑
        Object obj1 = getInstance(Dept.class);
        System.out.println(obj1); // 泛型.Dept@6d6f6e28
        Dept dept1 = (Dept) obj1;
        dept1.getName(); //Dept

        //TODO 注意  但是无法保证 getInstance() 的入参一定是Dept.class,这就导致返回的Obj做强制转换时候会异常发生。故代码不够健壮
        Object obj2 = getInstance(Classes.class); // Classes.class
        System.out.println(obj2); // 泛型.Classes@135fbaa4
//        Dept dept2 = (Dept) obj2; // Exception in thread "main" java.lang.ClassCastException: 泛型.Classes cannot be cast to 泛型.Dept
//        dept2.getName(); //Dept

        //TODO
        Dept dept3 = getInstance2(Dept.class);
        dept3.getName();  // Dept

    }
    //TODO  做返回值类型约束，增强代码健壮性
    public static <T> T getInstance2(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
    //TODO 不够健壮的代码 对返回类型没有做约束，返回的结果还要做强制成其他类型容易出现类型不匹配异常。
    public static Object getInstance(Class clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
    //TODO 注意点1：  java 泛型只会对声明泛型后面的代码起约束作用，不会对前面的代码起约束作用。
    public static void attention01(){
        //TODO 注意点  打印对象的时候不用考虑元素类型，使用里面的元素就要考虑元素类型了。
        //TODO 返回的是 List<Classes> ,但是接收的是List<Dept>，结果却能接收,这是因为
        // TODO java 泛型只会对声明泛型后面的代码起约束作用，不会对前面的代码起约束作用。
        List<Dept> list2 = getDatas(); // 返回的是 List<Classes> ,但是接收的是List<Dept>，结果却能接收
        System.out.println(list2); // [泛型.注意点$Classes@6d6f6e28] ,但是不报错
        list2.get(0).getName();  //Exception in thread "main" java.lang.ClassCastException: 泛型.注意点$Classes cannot be cast to 泛型.注意点$Dept

    }
    public static List getDatas(){
        List<Classes> list = new ArrayList<Classes>();
        list.add(new Classes());
        return list ;
    }

}
class Dept{
    public void getName(){
        System.out.println("Dept");
    }
}
class Classes{
    public void getName(){
        System.out.println("Classes");
    }
}