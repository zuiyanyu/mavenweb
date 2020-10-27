package com.DesignPattern.patterns.Command_Pattern.command;

import com.DesignPattern.patterns.Command_Pattern.receiver.Stock;

//创建实现了 Order 接口的实体类。
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}