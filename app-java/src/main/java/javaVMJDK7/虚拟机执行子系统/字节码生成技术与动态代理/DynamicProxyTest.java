package javaVMJDK7.虚拟机执行子系统.字节码生成技术与动态代理;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
    interface IHello{
        void sayHello(String name);
    }
 static class Hello implements IHello{
     @Override
     public void sayHello(String name) {
         System.out.println("hello "+name);
     }
 }

 static class DynamicProxy implements InvocationHandler{
        Object originalObj ;
        Object bind(Object originalObj){
            this.originalObj = originalObj ;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),originalObj.getClass().getInterfaces(),this);
        }
     @Override
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         System.out.println("==========args===========");
         System.out.println("args="+args[0]);

         System.out.println("========className=============");
         Class<?> proxyClass = proxy.getClass();
         String name = proxyClass.getName(); //name=javaVMJDK7.虚拟机执行子系统.字节码生成技术与动态代理.$Proxy0
         System.out.println("name="+name);

         System.out.println("===========declaredMethods==========");
         Method[] declaredMethods = proxyClass.getDeclaredMethods();
         for (Method declaredMethod : declaredMethods) {
             String name1 = declaredMethod.getName();
             System.out.println(name1);
         }
         System.out.println("=====================");
         System.out.println("welocom ");
         return method.invoke(originalObj,args);
     }
 }

    public static void main(String[] args) {
//        Properties properties = System.getProperties();
//        properties.put("sun.misc.ProxyGenerator.saveGeneratedFiles","true") ;
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "True");

        IHello hello = (IHello)new DynamicProxy().bind(new Hello());
        hello.sayHello("张三");


    }
}



/*
//
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
public final class $Proxy0 extends Proxy implements DynamicProxyTest.IHello
{
    private static Method m3;
    private static Method m1;
    private static Method m0;
    private static Method m2;
    public $Proxy0( InvocationHandler paramInvocationHandler) throws Throwable
    {
        super( paramInvocationHandler) ;
    }
    public final void sayHello( ) throws Throwable
    {
        try
        {
            this.h.invoke( this,m3, null) ;
            return;
        }
        catch( RuntimeException localRuntimeException)
        {
            throw localRuntimeException;
        }
        catch( Throwable localThrowable)
        {
            throw new UndeclaredThrowableException( localThrowable) ;
        }}
    //此处省略了 equals(), hashCode(),toString()三个方法代码，
    //这三个方法的内容与 sayHello(非常相似)
    static
    {
        try
        {
            m3=Class.forName( "org.fenixsoft.bytecode.DynamicProxyTest $IHello") .getMethod( "sayHello", new Class[0]) ;
            m1=Class.forName( "java.lang.Object") .getMethod( "equals", new Class[]{Class.forName( "java.lang.Object") }) ;
            m0=Class.forName( "java.lang.Object") .getMethod( "hashCode", new Class[0]) ;
            m2=Class.forName( "java.lang.Object") .getMethod( "toString", new Class[0]) ;
        }
        catch( NoSuchMethodException localNoSuchMethodException)
        {
            throw new NoSuchMethodError( localNoSuchMethodException.getMessage( ) ) ;
        }
        catch( ClassNotFoundException localClassNotFoundException)
        {
            throw new NoClassDefFoundError( localClassNotFoundException.getMessage( ) ) ;
        }
    }
}
*/
