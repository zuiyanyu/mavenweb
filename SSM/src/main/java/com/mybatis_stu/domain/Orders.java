package com.mybatis_stu.domain;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable {
    // 主键：订单id
    private Integer orderId;

    // 外键：用户id
    private Integer userId;

    // 本单中订购的商品数量
    private Integer numbers;

    // 下单时间
    private Date createTime;

     // tableName : orders
    private static final long serialVersionUID = 1L;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orders");
        sb.append(" [");
        sb.append("orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", numbers=").append(numbers);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}