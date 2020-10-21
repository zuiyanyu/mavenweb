package com.mybatis_stu.f_pageHelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mybatis_stu.domain.Orders;
import com.mybatis_stu.mapper.OrdersMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class PageHelperTest  {
    private ApplicationContext applicationContext;
    @Before
    public void setUp(){
        String xlmPath ="com/mybatis_stu/f_pageHelper/applicationContext.xml" ;
        applicationContext = new ClassPathXmlApplicationContext(xlmPath);
    }
    @Test
    public void findUserByIdTest2() throws Exception {
        //   单个mapper代理类配置
        OrdersMapper ordersMapper = applicationContext.getBean("ordersMapper", OrdersMapper.class);

    }
        @Test
    public void findUserByIdTest() throws Exception{
       //   单个mapper代理类配置
        OrdersMapper ordersMapper = applicationContext.getBean("ordersMapper", OrdersMapper.class);


        // PageHelper.startPage();后的第一条查询语句才会进行分页查询。
        Page<Object> pageConfig = PageHelper.startPage(1, 3);

        //当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
//        pageConfig.setPageSizeZero(true) ;
//        pageConfig.setPageSize(0);

        ////进行分页查询
        List<Orders> orders = ordersMapper.selectAllOrders();
        //查询结果只有1条
        for (Orders order : orders) {
            System.out.println(order);
            System.out.println("---------------");
        }

        //可以进行转型，page中保存了分页查询的结果
        Page<Orders> page = (Page<Orders>)orders;
        //Page{count=true, pageNum=1, pageSize=1, startRow=0, endRow=1, total=2, pages=2, countSignal=false, orderBy='null',
        // orderByOnly=false, reasonable=false, pageSizeZero=false}
        System.out.println(page);
        //获取分页查询的结果
        List<Orders> result = page.getResult();

        System.out.println(result.size());
        for (Orders order : result) {
            System.out.println("=========================");
            System.out.println(order);
        }
//        Page<Orders> page = (Page<Orders>)orders ;
//        System.out.println(page);


     }

}
