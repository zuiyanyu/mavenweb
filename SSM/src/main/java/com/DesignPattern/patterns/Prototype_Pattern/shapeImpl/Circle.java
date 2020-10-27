package com.DesignPattern.patterns.Prototype_Pattern.shapeImpl;

import com.DesignPattern.patterns.Prototype_Pattern.Shape;

public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}