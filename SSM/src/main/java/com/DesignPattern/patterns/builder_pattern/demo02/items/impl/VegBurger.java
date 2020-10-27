package com.DesignPattern.patterns.builder_pattern.demo02.items.impl;

import com.DesignPattern.patterns.builder_pattern.demo02.items.Burger;

//蔬菜汉堡
public class VegBurger extends Burger {

    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}