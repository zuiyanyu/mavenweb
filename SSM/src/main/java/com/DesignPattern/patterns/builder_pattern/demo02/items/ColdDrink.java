package com.DesignPattern.patterns.builder_pattern.demo02.items;

import com.DesignPattern.patterns.builder_pattern.demo02.pack.impl.Bottle;
import com.DesignPattern.patterns.builder_pattern.demo02.pack.Packing;

//冷饮
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }
}