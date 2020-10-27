package com.DesignPattern.patterns.builder_pattern.demo02.items.impl;

import com.DesignPattern.patterns.builder_pattern.demo02.items.Burger;

//鸡肉汉堡
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }
}