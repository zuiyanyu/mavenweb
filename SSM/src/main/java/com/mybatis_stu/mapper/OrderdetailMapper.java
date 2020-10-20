package com.mybatis_stu.mapper;

import com.mybatis_stu.domain.Orderdetail;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Integer orderdetailId);

    int insert(Orderdetail record);

    int insertSelective(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer orderdetailId);

    int updateByPrimaryKeySelective(Orderdetail record);

    int updateByPrimaryKey(Orderdetail record);
}