package com.mybatis_stu.domain.vo;

import com.mybatis_stu.domain.Items;
import com.mybatis_stu.domain.Orderdetail;
import com.mybatis_stu.domain.Orders;

public class OrderdetailVO extends Orderdetail {

    //订单表(从表)  一对一的关系
    private Orders orders ;

    //商品表（从表） 一对一的关系
    private Items items ;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return super.toString()+"\n\t + OrderdetailVO{" +
                "orders=" + orders +
                ", items=" + items +
                '}';
    }
}
