package com.spring_stu.spring_PropertyEditor.customPropertyEditor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 我们希望在配置Boss，不通过引用Bean的方式注入Boss的car属性，而是希望直接通过字符串字面值提供配置。
 *
 * spring拥有String类型的默认属性编辑器，因此对于String类型的属性我们不操心。
 * 但是Car类型是我们自己定义的类型，要配置Boss的car属性，有两种方案：
 * 1）在配置文件中为Car专门配置一个<bean>，然后再boss的<bean>中通过red引用car的Bean.
 * 2) 为Car类型提供一个自定义的属性编辑器，这样，我们就通过字面值为Boss的car属性提供配置值。
 *
 */
@Component
public class Boss {
    @Value("${c3p0.user}")
    private String name ;
    @Value("红旗,200,20003.0")
    private Car car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
