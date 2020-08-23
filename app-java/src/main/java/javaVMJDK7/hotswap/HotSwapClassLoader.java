package javaVMJDK7.hotswap;

/**第一个类HotSwapClassLoader：实现 “同一个类的代码可以被多次加载” 这个需求。
 *
 * 为了多次载入执行类而加入的 类加载器
 * 把defineClass方法开放出来，只有外部显示调用的时候才会使用到loadByte方法
 * 由虚拟机调用时候，仍然按照原有的双亲委派规则使用 loadClass方法进行类加载
 *
 * 1. HotSwapClassLoader 所做的事情仅仅是公开父类(即java.lang.ClassLoader)中的protected方法 defineClass().
 *    -- 使用这个方法把提交执行的Java类的byte[]数组转变为Class对象。
 * 2. HotSwapClassLoader中并没有重写 loadClass()或者findClass()方法，因此如果不算外部手工调用loadByte()方法的话，
 *    这个类加载器的类查找范围与它的父类加载器是完全一致的。
 *    -- 在被虚拟机调用时，他会按照双亲委派模型交给父类加载。
 * 3. 构造函数中指定为“加载HotSwapClassLoader类的类加载器”作为父类加载器。
 *     -- 这一步是实现提交的执行代码可以访问服务端引用类库的关键。
 */
public class HotSwapClassLoader extends ClassLoader {
    //使用指定的类加载器 进行类加载
    public HotSwapClassLoader(){
        super(HotSwapClassLoader.class.getClassLoader());
    }
    //加载类字节码，创建类
    public Class loadByte(byte[] classByte){
        return defineClass(null,classByte,0,classByte.length);
    }
}
