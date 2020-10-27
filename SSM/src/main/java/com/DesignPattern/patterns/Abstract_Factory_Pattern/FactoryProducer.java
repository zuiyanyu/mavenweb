package com.DesignPattern.patterns.Abstract_Factory_Pattern;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.factory.AbstractFactory;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.factory.ColorFactory;
import com.DesignPattern.patterns.Abstract_Factory_Pattern.factory.ShapeFactory;

public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}