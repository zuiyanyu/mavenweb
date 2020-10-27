package com.DesignPattern.patterns.builder_pattern.demo02.items.impl;

import com.DesignPattern.patterns.builder_pattern.demo02.items.ColdDrink;

//可口可乐（coke）
public class Coke extends ColdDrink {

    @Override
    public float price() {
        return 30.0f;
    }

    @Override
    public String name() {
        return "Coke";
    }
}