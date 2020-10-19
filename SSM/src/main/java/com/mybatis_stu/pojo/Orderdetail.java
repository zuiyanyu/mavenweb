package com.mybatis_stu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Orderdetail implements Serializable {
    //多对多查询：在Orderdetail中添加Items items；
    //一个订单详情 对应一个商品
    Items items;

    // 主键：订单商品详情id
    private Integer orderdetailId;

    // 外键：订单id
    private Integer orderId;

    // 外键：商品id
    private Integer itemsId;

    // 订单商品状态：0-订单已取消，1-待配送，2-配送中，3-已配送
    private String orderStatus;

    // 用户行为：1-正常配送，2-取消订单，3-延迟配送，4-催单
    private String orderAction;

    // 购买时商品名称
    private String itemName;

    // 购买时商品价格
    private Double itemPrice;

    // 下单时间
    private Date createTime;

     // tableName : orderdetail
    private static final long serialVersionUID = 1L;

    public Items getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "items=" + items +
                ", orderdetailId=" + orderdetailId +
                ", orderId=" + orderId +
                ", itemsId=" + itemsId +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderAction='" + orderAction + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", createTime=" + createTime +
                '}';
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderdetailId() {
        return orderdetailId;
    }

    public void setOrderdetailId(Integer orderdetailId) {
        this.orderdetailId = orderdetailId;
    }

    public Integer getItemsId() {
        return itemsId;
    }

    public void setItemsId(Integer itemsId) {
        this.itemsId = itemsId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderAction() {
        return orderAction;
    }

    public void setOrderAction(String orderAction) {
        this.orderAction = orderAction;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}