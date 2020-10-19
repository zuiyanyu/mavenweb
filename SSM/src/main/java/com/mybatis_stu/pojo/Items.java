package com.mybatis_stu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Items implements Serializable {
    // 主键：商品id
    private Integer itemId;

    // 当前商品名称
    private String itemName;

    // 当前商品价格
    private Double itemPrice;

    // 下单时间
    private Date createTime;

     // tableName : items
    private static final long serialVersionUID = 1L;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("itemId=").append(itemId);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemPrice=").append(itemPrice);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}