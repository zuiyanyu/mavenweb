package com.DesignPattern.patterns.Prototype_Pattern.shapeImpl;

import com.DesignPattern.patterns.Prototype_Pattern.Shape;

public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
