package com.DesignPattern.patterns.Command_Pattern;

import com.DesignPattern.patterns.Command_Pattern.invoker.Broker;
import com.DesignPattern.patterns.Command_Pattern.command.BuyStock;
import com.DesignPattern.patterns.Command_Pattern.command.SellStock;
import com.DesignPattern.patterns.Command_Pattern.receiver.Stock;

//客户端 client
public class CommandPatternClient {
    public static void main(String[] args) {
        // Stock 是receiver 命令执行者
        Stock abcStock = new Stock();

        //buyStockOrder 和 sellStockOrder 是command命令
        BuyStock buyStockOrder = new BuyStock(abcStock);
        SellStock sellStockOrder = new SellStock(abcStock);

        //broker 是invoker  使用命令对象的入口
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        //使用命令
        broker.placeOrders();
    }
}