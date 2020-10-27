package com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Shape;

public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
