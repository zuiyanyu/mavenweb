package com.DesignPattern.patterns.Command_Pattern.command;

import com.DesignPattern.patterns.Command_Pattern.receiver.Stock;

//创建实现了 Order 接口的实体类。
public class BuyStock implements Order {
    //命令的执行者
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}
