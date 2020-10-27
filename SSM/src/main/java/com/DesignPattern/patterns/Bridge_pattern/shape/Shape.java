package com.DesignPattern.patterns.Bridge_pattern.shape;

import com.DesignPattern.patterns.Bridge_pattern.drawAPI.DrawAPI;

//使用 DrawAPI 接口创建抽象类 Shape。
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }
    public abstract void draw();
}
