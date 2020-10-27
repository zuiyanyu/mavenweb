package com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Impl;

import com.DesignPattern.patterns.Abstract_Factory_Pattern.product.Color;

public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}