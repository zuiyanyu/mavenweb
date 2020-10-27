package com.DesignPattern.patterns.Iterator_Pattern;

//容器  容器都应该有元素访问的功能。
public interface Container {
    public Iterator getIterator();
}