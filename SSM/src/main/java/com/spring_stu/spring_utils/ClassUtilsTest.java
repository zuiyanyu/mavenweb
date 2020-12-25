package com.spring_stu.spring_utils;

import org.junit.Test;
import org.springframework.util.ClassUtils;

public class ClassUtilsTest extends ClassUtils {


    @Test
    //将类的全限定名 替换为相类文件所在路径
    public void convertClassNameToResourcePath() {
        String className = "com.spring_stu.dao.AccountDao";
        String s = ClassUtils.convertClassNameToResourcePath(className);
        System.out.println(s);//com/spring_stu/dao/AccountDao
        String classFile = s + ".class";
    }
}
