package com.DesignPattern.patterns.Abstract_Factory_Pattern.factory;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Color;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl.Blue;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl.Green;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl.Red;

public class ColorFactory extends AbstractFactory {

//    @Override
//    public Shape getShape(String shapeType){
//        return null;
//    }

    @Override
    public Color getColor(String color) {
        if(color == null){
            return null;
        }
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }
}