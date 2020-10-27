package com.DesignPattern.patterns.builder_pattern.demo01.builder.concreteBuilder;

import com.DesignPattern.patterns.builder_pattern.demo01.builder.PersonBuilder;
import com.DesignPattern.patterns.builder_pattern.demo01.product.Person;
import com.DesignPattern.patterns.builder_pattern.demo01.product.Women;
/**
 * TODO 创建实现类WomenBuilder和ManBuilder来实现PersonBuilder接口中的方法，即 ConcreteBuilder
 */
public class WomenBuilder implements PersonBuilder {

    Person person;

    public WomenBuilder() {
        person=new Women();
    }

    @Override
    public void buildHead() {
        person.setHead("建造女神的头");
    }

    @Override
    public void buildBody() {
        person.setBody("建造女神的身体");
    }

    @Override
    public void buildFoot() {
        person.setFoot("建造女神的脚");
    }

    @Override
    public Person builderPerson() {
        return person;
    }

}
