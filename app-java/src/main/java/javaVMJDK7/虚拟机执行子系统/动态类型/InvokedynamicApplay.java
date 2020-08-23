package javaVMJDK7.虚拟机执行子系统.动态类型;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

public class InvokedynamicApplay {
    public static void main(String[] args) {
        Son son = new Son();
        son.thinking();
    }

}
class GrandFather{
      void thinking(){
        System.out.println("i am GrandFather");
    }
}
class Father extends GrandFather{
      void thinking(){
        System.out.println("i am father ");
    }
}
class Son extends  Father{
      void thinking(){
        //不修改其他代码，实现调用祖父类的thinking()方法。
        try {
            MethodType mt = MethodType.methodType(void.class);
            // （目标方法所属类，目标方法名，目标方法类型，目标方法调用类）.bindTo(当前实例对象)
            //MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,getClass()).bindTo(this) ;
            MethodHandle mh = lookup().findSpecial(GrandFather.class,"thinking",mt,Son.class).bindTo(this) ;
            mh.invoke();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
