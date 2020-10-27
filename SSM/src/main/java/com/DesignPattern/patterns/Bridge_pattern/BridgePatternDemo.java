package com.DesignPattern.patterns.Bridge_pattern;

import com.DesignPattern.patterns.Bridge_pattern.drawAPI.GreenCircle;
import com.DesignPattern.patterns.Bridge_pattern.drawAPI.RedCircle;
import com.DesignPattern.patterns.Bridge_pattern.shape.Circle;
import com.DesignPattern.patterns.Bridge_pattern.shape.Shape;

//使用 Shape 和 DrawAPI 类画出不同颜色的圆。
public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100, 10, new RedCircle());
        Shape greenCircle = new Circle(100,100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}