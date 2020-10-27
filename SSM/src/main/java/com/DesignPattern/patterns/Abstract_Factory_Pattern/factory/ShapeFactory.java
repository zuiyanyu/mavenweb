package com.DesignPattern.patterns.Abstract_Factory_Pattern.factory;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl.Circle;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl.Rectangle;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl.Square;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Shape;

public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
//
//    @Override
//    public Color getColor(String color) {
//        return null;
//    }
}