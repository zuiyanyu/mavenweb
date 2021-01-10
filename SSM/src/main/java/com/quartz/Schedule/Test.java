package com.quartz.Schedule;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;

public class Test {
    public static void main(String[] args) throws IntrospectionException {
        String prefix ="org.quartz.dataSource";
        if (!prefix.endsWith(".")) {
            prefix += ".";
        }
        HashSet<String> groups = new HashSet<String>(10);
        String key = "org.quartz.dataSource.qzDS.driver";

        if (key.startsWith(prefix)) {
            String groupName = key.substring(prefix.length(), key.indexOf(
                    '.', prefix.length()));
            groups.add(groupName);
        }
        System.out.println(groups);

        BeanInfo bi = Introspector.getBeanInfo(Test.class);
        PropertyDescriptor[] propDescs = bi.getPropertyDescriptors();
        for (PropertyDescriptor propDesc : propDescs) {
            Method writeMethod = propDesc.getWriteMethod();
            System.out.println(writeMethod);
        }
     }
    private String userName ;
    private Integer age ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
