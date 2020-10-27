package com.DesignPattern.patterns.builder_pattern.demo01;

import com.DesignPattern.patterns.builder_pattern.demo01.builder.PersonBuilder;
import com.DesignPattern.patterns.builder_pattern.demo01.product.Person;

//TODO 建造者模式与工程模式的区别:工厂模式关注的是创建单个产品，而建造者模式则关注创建符合对象，多个部分。
//TODO 创建PersonDirector 来指定你要创建的是Women还是Man,即Director
public class PersonDirector {
    /**
     * 构造人
     * @param pb builder
     * @return
     */
    public Person constructPerson(PersonBuilder pb) {
        pb.buildHead();
        pb.buildBody();
        pb.buildFoot();
        return pb.builderPerson();
    }
}
