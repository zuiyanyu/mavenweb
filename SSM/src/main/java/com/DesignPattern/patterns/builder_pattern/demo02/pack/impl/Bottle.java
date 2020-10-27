package com.DesignPattern.patterns.builder_pattern.demo02.pack.impl;

import com.DesignPattern.patterns.builder_pattern.demo02.pack.Packing;

public class Bottle implements Packing {

    @Override
    public String pack() {
        return "Bottle";
    }
}
