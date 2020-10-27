package com.DesignPattern.patterns.builder_pattern.demo01.builder.concreteBuilder;

import com.DesignPattern.patterns.builder_pattern.demo01.builder.PersonBuilder;
import com.DesignPattern.patterns.builder_pattern.demo01.product.Man;
import com.DesignPattern.patterns.builder_pattern.demo01.product.Person;

/**
 * TODO 创建实现类WomenBuilder和ManBuilder来实现PersonBuilder接口中的方法，即 ConcreteBuilder
 */
public class ManBuilder implements PersonBuilder {
    Person person;

    public ManBuilder() {        person=new Man();    }
    @Override
    public void buildHead() {        person.setHead("建造男神的头");    }
    @Override
    public void buildBody() {        person.setBody("建造男神的身体");  }
    @Override
    public void buildFoot() {        person.setFoot("建造男神的脚");    }
    @Override
    public Person builderPerson() {
        return person;
    }
}
