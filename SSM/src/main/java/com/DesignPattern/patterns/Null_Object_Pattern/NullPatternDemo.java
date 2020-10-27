package com.DesignPattern.patterns.Null_Object_Pattern;

/**
 * 我们将创建一个定义操作（在这里，是客户的名称）的 AbstractCustomer 抽象类，和
 * 扩展了 AbstractCustomer 类的实体类。工厂类 CustomerFactory 基于客户传递的名字来返回 RealCustomer 或 NullCustomer 对象。
 *
 * NullPatternDemo，我们的演示类使用 CustomerFactory 来演示空对象模式的用法。
 */
public class NullPatternDemo {
    public static void main(String[] args) {

        AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
        AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
        AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
        AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

        System.out.println("Customers");
        System.out.println(customer1.getName());
        System.out.println(customer2.getName());
        System.out.println(customer3.getName());
        System.out.println(customer4.getName());
    }
}