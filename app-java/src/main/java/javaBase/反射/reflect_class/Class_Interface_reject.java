package javaBase.反射.reflect_class;

import java.lang.reflect.Type;

public class Class_Interface_reject {
    Class clazz ;
    {
        try {
            clazz = Class.forName("javaBase.反射.domain.Person") ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void a(){
        Type[] genericInterfaces = clazz.getGenericInterfaces();
    }
}
