package com.mybatis_stu.domain.vo;

import com.mybatis_stu.domain.Orders;
import com.mybatis_stu.domain.User;

import java.util.List;

public class UserVO extends User {
    //订单表    一个用户可以有多个订单  一对多的关系（主表：用户表，从表：订单表）
    private List<Orders> ordersList ;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public String toString() {
        return super.toString()+"\n\t + UserVO{" +
                "ordersList=" + ordersList +
                '}';
    }
}
