package com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Color;

public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}