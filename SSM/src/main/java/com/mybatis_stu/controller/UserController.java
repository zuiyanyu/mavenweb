package com.mybatis_stu.controller;

import com.mybatis_stu.domain.User;
import com.mybatis_stu.domain.vo.UserQueryVO;
import com.mybatis_stu.domain.vo.UserVO;
import com.mybatis_stu.mapper.UserMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserController extends MapperBaseTest {
    //1、 先添加用户，然后获取生成的主键
    @Test
    public void getIdAfterInsert() throws Exception {
        UserMapper mapper = getMapper(UserMapper.class);

        User user = new User();
        user.setUserName("王五");
        user.setAddress("郑州");
        user.setBirthday(new Date());


        mapper.getIdAfterInsert_type1(user);
        System.out.println(user);

        mapper.getIdAfterInsert_type2(user);
        System.out.println(user);
    }

    // 2、 先生成主键key, 然后添加用户(使用新生成的key作为主键)
    @Test
    public void getIdBeforeInsert() throws Exception {
        UserMapper mapper = getMapper(UserMapper.class);

        User user = new User();
        user.setUserName("王五");
        user.setAddress("郑州");
        user.setUserAge(22);
        user.setBirthday(new Date());

        mapper.getIdBeforeInsert(user);
        System.out.println(user);
    }

    //3.入参为VO类
    @Test
    public void findUserList() throws IOException {
        UserMapper mapper = getMapper(UserMapper.class);

        UserQueryVO userQueryVO = new UserQueryVO();
        List<Integer> idList = Arrays.asList(2, 3, 4, 5, 6, 7);
        userQueryVO.setIdList(idList);

        User user = new User();
        user.setUserName("王五");
        userQueryVO.setUser(user);

        List<User> userList = mapper.findUserList(userQueryVO);
        for (User user1 : userList) {
            System.out.println(user1);
        }
        //关闭资源
        closeSqlSession();
    }

    //4.resultMap入门
    @Test
    public void findUserRstMap(){
        UserMapper mapper = getMapper(UserMapper.class);
        User userRstMap = mapper.findUserRstMap(2);
        System.out.println(userRstMap);
    }
    //一对多的关系：6.查询用户，并查询用户的订单信息  一个用户有多个订单
    @Test
    public void getUserWithOrders(){
        UserMapper mapper = getMapper(UserMapper.class);
        List<UserVO> userWithOrdersMapList = mapper.getUserWithOrders();
        userWithOrdersMapList.forEach(System.out::println);
     }

    /**
     * 7 .多对多的映射
     * 主信息：user
     * 从信息：items、orders、orderdetail
     * @return
     */
    @Test
    public void findUserWithItems(){
        UserMapper mapper = getMapper(UserMapper.class);
        List<com.mybatis_stu.pojo.User> userWithItemsList = mapper.findUserWithItems();
        userWithItemsList.forEach(System.out::println);
    }

}
