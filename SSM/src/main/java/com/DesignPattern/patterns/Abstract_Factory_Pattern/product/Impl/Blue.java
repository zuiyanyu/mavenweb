package com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Color;

public class Blue implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Blue::fill() method.");
    }
}