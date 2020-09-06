package javaBase.对象与类.反射;

import javaBase.domain.Employee;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayTest {
    /**
     *  int[] a = { 1,2, 3, 4, 5 };
     *  a = (int[]) arrayCopyOf(a,10);
     *为了能够实现上述操作，应该将 arrayCopyOf 的参数声明为 Object 类型，.而不要声明为
     * 对象型数组（Object[])。整型数组类型 int[] 可以被转换成 Object ，但不能转换成对象数组。
     */
    @Test
    public void arrayCopyOfTest(){
        Employee[] employeesArray= new Employee[10] ;
        employeesArray[0] = new Employee("张三") ;
        System.out.println("==========数组拷贝结果的处理==============");
        //TODO 将拷贝的数组强制转为
        Employee[] newArray = (Employee[])arrayCopyOf(employeesArray, employeesArray.length + 1);
        System.out.println("resultArray.length = "+newArray.length);
        Employee employee = newArray[0];
        System.out.println("employee name = "+employee.getName());

        System.out.println("=======================================");

        int[] a = { 1,2, 3, 4, 5 };
        a = (int[]) arrayCopyOf(a,10);
        System.out.println("a.length = "+a.length);
        System.out.println("a = "+Arrays.toString(a));
    }
    /**
     * TODO 4. 使用反射编写泛型数组代码  数组的拷贝功能
     * TODO a.将一个 Employee[]临时地转换成 Object[ ] 数组， 然后再把它转换回来是可以的;
     * TODO b.但一个从开始就是 Object[] 的数组却永远不能转换成 Employe [] 数组。
     *  java.lang.reflect 包中 Array 类的一些方法。其中最关键的是 Array类中的静态方法 newlnstance,
     * 它能够构造新数组。在调用它时必须提供两个参数，一个是数组的元素类型，一个是数组的
     * 长度。
     * Object newArray = Array.newlnstance(componentType, newLength) ;
     * 为了能够实际地运行，需要获得新数组的长度和元素类型。
     * 可以通过调用 Array.getLength(a) 获得数组的长度， 也可以通过 Array类的静态 getLength
     * 方法的返回值得到任意数组的长度。而要获得新数组元素类型，就需要进行以下工作：
     * 1 ) 首先获得 a 数组的类对象。
     * 2 ) 确认它是一个数组。
     * 3 ) 使用 Class 类（只能定义表示数组的类对象）的 getComponentType 方法确定数组对应
     * 的类型。
     *
     *
     * @param
     */

    public Object arrayCopyOf(Object obj,int newLength){
        // =========================== ...  入参  Object[] employeesArray
        Class arrClass = obj.getClass();

        //TODO 使用 arrClass 创建一个新的数组，并拷贝原始值到新数组中
        //TODO 1. 判断是 arrClass 是否是数组class
        boolean isArray = arrClass.isArray();
        System.out.println("isArray="+isArray);
        if(!isArray) {
            System.out.println("入参 obj 不是数组的Class类实例！");
            return null;
        }

        //TODO 2. 获取数组的长度
        int length = Array.getLength(obj);
        System.out.println("array length ="+length);

        //TODO 3. 获取数组的元素类型
        Class<?> componentType = arrClass.getComponentType();
        System.out.println("componentType = "+componentType.getName());

        //TODO 4. 创建一个新数组(元素类型和新数组一样)
        newLength = newLength > 0?newLength: length +1 ;
        Object newArray = Array.newInstance(componentType, newLength);
        //TODO 5. 将原数组中的内容拷贝到新的数组中
        System.arraycopy(obj,0,newArray,0,Math.min(length,newLength));

        /**TODO 注意：
         * 这种创建和拷贝数组值的方式是不行的。
         * ObjectD newArray = new Object[newlength]:
         * System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
         *
         * //对返回结果进行如下处理的时候报异常：ClassCastException 异常
         * Employee employee = (Employee[])newArray;
         * TODO 原因：
         * TODO a.将一个 Employee[]临时地转换成 Object[ ] 数组， 然后再把它转换回来是可以的;
         * TODO b.但一个从开始就是 Object[] 的数组却永远不能转换成 Employe [] 数组。
         */

        return newArray;
        // ======================== 返回结果  newArray
    }
}
