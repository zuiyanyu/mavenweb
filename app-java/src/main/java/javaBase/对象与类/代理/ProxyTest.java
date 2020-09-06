package javaBase.对象与类.代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * TODO java.Iang.reflect.InvocationHandler 1.3
 *      • Object invoke(Object proxy,Method method,0bject[] args)
 *          定义了代理对象调用方法时希望执行的动作。
 *TODO java.Iang.reflect.Proxy 1.3
 *      • static Class<?> getProxyClass(Cl assLoader loader, Class<?>...interfaces)
 *          返回实现指定接口的代理类。
 *      • static Object newProxyInstance(ClassLoader loader, Class<?>[]interfaces, InvocationHandler handler)
 *          构造实现指定接口的代理类的一个新实例。
 *          所有方法会调用给定处理器对象的 invoke 方法。
 *      • static boolean isProxyClass(Class<?> cl)
 *          如果 cl 是一个代理类则返回 true。
 */
public class ProxyTest implements ProxyInterface {
    public static void main(String[] args) {
       //实例1
        TraceHandler<ProxyInterface> proxyTestTraceHandler = new TraceHandler<>();
        ProxyInterface proxyInterface = proxyTestTraceHandler.bindTo(new ProxyTest());
        String name = proxyInterface.getName();
        System.out.println("name = "+ name );


        //实例2
        Comparable value = new Comparable(){
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        } ;
        TraceHandler<Comparable> handler = new TraceHandler() ;
        Class[] interfaces = new Class[] { Comparable.class};
        Object o = Proxy.newProxyInstance(null, interfaces, handler);
    }
    private String name ="zhangsan";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
interface ProxyInterface{
     String getName();
    void setName(String name);
}
class TraceHandler<T>  implements InvocationHandler {
    T target ;
    public TraceHandler(){

    }
    public T  bindTo(T target){
        this.target = target ;

        Class[] interfaces =target.getClass().getInterfaces();
        T  proxy = (T)Proxy.newProxyInstance(this.getClass().getClassLoader() , interfaces , this);
        return proxy ;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(this.target,args);
    }
}