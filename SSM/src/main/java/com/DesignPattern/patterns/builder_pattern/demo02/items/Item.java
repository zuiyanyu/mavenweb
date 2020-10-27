package com.DesignPattern.patterns.builder_pattern.demo02.items;

import com.DesignPattern.patterns.builder_pattern.demo02.pack.Packing;

public interface Item {
    //商品名称
    public String name();

    //商品包装
    public Packing packing();

    //商品价格
    public float price();
}