package com.DesignPattern.patterns.Memento_Pattern;
//备忘录
public class Memento {
    private String state;

    //设置和获取状态
    public Memento(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
}