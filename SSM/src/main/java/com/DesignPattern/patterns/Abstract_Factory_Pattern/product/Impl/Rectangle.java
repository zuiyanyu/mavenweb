package com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Shape;

public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}