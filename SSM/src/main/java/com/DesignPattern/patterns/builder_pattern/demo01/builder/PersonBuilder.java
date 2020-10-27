package com.DesignPattern.patterns.builder_pattern.demo01.builder;

import com.DesignPattern.patterns.builder_pattern.demo01.product.Person;

/**
 * 创建接口PersonBuilder，其中包含需要创建的 head body foot   即  Builder
 */
public interface PersonBuilder {
    void buildHead();
    void buildBody();
    void buildFoot();

    Person builderPerson();
}
