package com.mybatis_stu.controller;

import com.mybatis_stu.domain.Orders;
import com.mybatis_stu.domain.vo.OrdersVO;
import com.mybatis_stu.mapper.OrdersMapper;
import org.junit.Test;

import java.util.List;

public class OrdersController extends MapperBaseTest {
    //4.resultMap入门
    @Test
    public void findUserRstMap(){
        OrdersMapper mapper = getMapper(OrdersMapper.class);
        Orders orders = mapper.selectByPrimaryKey(2);
        System.out.println(orders);
    }

    //一对一查询：查询订单详情，并查出下订单的用户信息
    //下订单的用户  一个订单只属于一个用户，是一对一的关系(订单是主表，用户是从表)
    @Test
    public void getOrdersWithUser(){
        OrdersMapper mapper = getMapper(OrdersMapper.class);
        List<OrdersVO> ordersWithUserList = mapper.getOrdersWithUser(null);
        ordersWithUserList.forEach(System.out::println);
     }

    //一对多查询
    //查询定单以及订单详情和订单用户   订单表:用户表(一对一)  订单表:订单详情表(一对多)
    @Test
    public void getOrdersWithUserAndDetail(){
        OrdersMapper mapper = getMapper(OrdersMapper.class);
        List<OrdersVO> ordersWithUserList = mapper.getOrdersWithUserAndDetail();
        ordersWithUserList.forEach(System.out::println);
    }
}
