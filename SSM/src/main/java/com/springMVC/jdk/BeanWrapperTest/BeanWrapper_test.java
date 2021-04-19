package com.springMVC.jdk.BeanWrapperTest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

public class BeanWrapper_test {
    public static void main(String[] args) {
        //TODO 设置直接属性值
        //BeanWrapper company = new BeanWrapperImpl(new Company());
        BeanWrapperImpl company = new BeanWrapperImpl(new Company());
        company.setPropertyValue("name", "Some Company Inc.");
        // 或者
        PropertyValue value = new PropertyValue("name", "Some Company Inc.");
        company.setPropertyValue(value);

        BeanWrapper jim = new BeanWrapperImpl(new Employee());
        jim.setPropertyValue("name", "Jim Stravinsky");
        jim.setPropertyValue("salary", 120.3);
        //获取被包装的实例对象
        company.setPropertyValue("managingDirector", jim.getWrappedInstance());

        //TODO 获取属性值
        // retrieving the salary of the managingDirector through the company
        Float salary = (Float) company.getPropertyValue("managingDirector.salary");
        String nameList =  (String)company.getPropertyValue("nameList[1]");
        String mapValue = (String) company.getPropertyValue("map[a]");
        //120.3=== lisi ===A
        System.out.println(""+salary + "=== " + nameList + " ===" + mapValue);

        company.setPropertyValue("dataTime","java.util.Date");




    }
}
