package com.mybatis_stu.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
    //多对多查询：在Orders类中添加List<Orderdetail> detailList;
    //一个订单多个 订单详情
    List<Orderdetail> detailList;

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


    @Override
    public String toString() {
        return "Orders{" +
                "detailList=" + detailList +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", numbers=" + numbers +
                ", createTime=" + createTime +
                '}';
    }

    public List<Orderdetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Orderdetail> detailList) {
        this.detailList = detailList;
    }

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
}