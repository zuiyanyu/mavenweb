package com.mybatis_stu.domain.vo;

import com.mybatis_stu.domain.Orderdetail;
import com.mybatis_stu.domain.Orders;
import com.mybatis_stu.domain.User;

import java.util.List;

public class OrdersVO extends Orders {
    // 用户名
    private String userName;

    // 下订单的用户  一个订单只属于一个用户，是一对一的关系(订单是主表，用户是从表)
    private User user;

    // 订单中的商品详情  一个订单包含多个商品，是一对多的关系(订单是主表，商品详情表是从表)
    private List<Orderdetail> orderdetailList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    @Override
    public String toString() {
        return super.toString()+"\n\t + OrdersVO{" +
                "userName='" + userName + '\'' +
                ", user=" + user +
                ", orderdetailList=" + orderdetailList +
                '}';
    }
}
