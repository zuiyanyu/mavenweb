package com.mybatis_stu.mapper;

import com.mybatis_stu.domain.Orders;
import com.mybatis_stu.domain.vo.OrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {
    //根据主键查询订单信息
    Orders selectByPrimaryKey(Integer orderId);



    //查询所有的订单
    List<Orders> selectAllOrders();

    //一对一查询：查询订单详情，并查出下订单的用户信息
    List<OrdersVO> getOrdersWithUser(@Param("orderId") Integer orderId);

    //懒加载 一对一查询：查询订单详情，并查出下订单的用户信息
    List<OrdersVO> getOrdersWithUser2(@Param("orderId") Integer orderId);


    //查询定单以及订单详情和订单用户   订单表:用户表(一对一)  订单表:订单详情表(一对多)
    List<OrdersVO> getOrdersWithUserAndDetail();

}