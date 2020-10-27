package com.DesignPattern.patterns.builder_pattern.demo02.items;

import com.DesignPattern.patterns.builder_pattern.demo02.pack.Packing;
import com.DesignPattern.patterns.builder_pattern.demo02.pack.impl.Wrapper;

//汉堡
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }
}