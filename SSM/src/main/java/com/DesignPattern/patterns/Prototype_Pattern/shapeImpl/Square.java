package com.DesignPattern.patterns.Prototype_Pattern.shapeImpl;

import com.DesignPattern.patterns.Prototype_Pattern.Shape;

public class Square extends Shape {

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}