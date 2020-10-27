package com.DesignPattern.patterns.builder_pattern.demo02.items.impl;

import com.DesignPattern.patterns.builder_pattern.demo02.items.ColdDrink;

//百事可乐（pepsi）
public class Pepsi extends ColdDrink {

    @Override
    public float price() {
        return 35.0f;
    }

    @Override
    public String name() {
        return "Pepsi";
    }
}