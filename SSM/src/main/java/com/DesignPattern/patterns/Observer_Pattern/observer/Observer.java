package com.DesignPattern.patterns.Observer_Pattern.observer;

import com.DesignPattern.patterns.Observer_Pattern.Subject;

//观察者
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}