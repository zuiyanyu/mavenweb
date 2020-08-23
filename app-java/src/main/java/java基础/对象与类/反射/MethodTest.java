package java基础.对象与类.反射;

import java基础.domain.Employee;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TODO 1. 反射机制允许你调用任意方法。
 * TODO 2. 在 Method 类中有一个 invoke 方法， 它允许调用包装在当前 Method 对象中的方法。
 * TODO 3.invoke 方法的签名是：
 *         3.1 Object invoke(Object obj, Object... args)  第一个参数是隐式参数， 其余的对象提供了显式参数
 *         3.2 对于静态方法，第一个参数可以被忽略， 即可以将它设置为 null
 * TODO 4. 如果返回类型是基本类型， invoke 方法会返回其包装器类型。
 *         double s = (Double) m2,invoke(harry);
 */
public class MethodTest {
    @Test
    public void methodInvokeTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<Employee> employeeClass = Employee.class;
        Method getNameMethod = employeeClass.getDeclaredMethod("getName");

        //3.1 Object invoke(Object obj, Object... args)  第一个参数是隐式参数， 其余的对象提供了显式参数
        Employee employee = new Employee("张三");
        Object invoke = getNameMethod.invoke(employee);
        System.out.println(invoke);

        // 3.2 对于静态方法，第一个参数可以被忽略， 即可以将它设置为 null .
        //  在使用包装器传递基本类型的值时， 基本类型的返回值必须是未包装的。Integer.class 和 int.class 不相等
        Method staticMethod = employeeClass.getDeclaredMethod("staticMethod",int.class);
        Object invoke1 = staticMethod.invoke(null,1);
        System.out.println(invoke1);
    }
}
