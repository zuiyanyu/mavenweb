package com.mavenweb.dao;

import com.mavenweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 这里重点了解 Spring JDBC的JdbcTemplate 模板类。
 *
 * 1. 传统的JDBC API太底层，用户执行每一条命令都需要执行如下过程：
 * ->获取连接Connection-> 创建Statement -> 执行数据操作 -> 获取结果集ResultSet->
 * ->从结果集中获取具体的结果数据(主要想要的东西)
 * -> 关闭结果集ResultSet ->关闭Statement ->  关闭Connection连接。
 *   Connection connection = DriverManager.getConnection("");
 *   PreparedStatement preparedStatement = connection.prepareStatement("");
 *   ResultSet resultSet = preparedStatement.executeQuery();
 *   resultSet.close(); preparedStatement.close();connection.close();
 *
 * 2. JDBC的JdbcTemplate 对传统的 JDBC API进行了薄层的封装，将样板的代码和那必不可少的代码进行了分离。
 *    JdbcTemplate 中封装了样板式的代码，用户通过样板类就可以轻松的完成大部分数据访问的操作。
 *    (用户不必关心 获取连接，关闭连接，异常处理等)
 *
 *
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //登录验证
    public int getMatchCount(String userName,String password){
        String sqlStr = "select user_name as count from t_user where user_name =? and password =?" ;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sqlStr, new Object[]{userName, password});

        return maps.size();
    }
    public  List<User> findUserByUserName(final String userName){
//        String sqlStr = "SELECT user_id,user_name,credits FROM t_user WHERE 1=1 or user_name =?" ;
        String sqlStr = "SELECT user_id,user_name,credits FROM t_user WHERE 1=1 and user_name =?" ;

        final List<User> userList = new ArrayList<>() ;
        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            //匿名类方式实现的回调函数   将查询的结果集回传到这里
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                User user0 = new User();
                user0.setUserId(rs.getInt("user_id"));
                user0.setUserName(rs.getString("user_name"));
                user0.setCredits(rs.getInt("credits"));
                userList.add(user0);

                while(rs.next()){
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUserName(rs.getString("user_name"));
                    user.setCredits(rs.getInt("credits"));

                    userList.add(user);
                }
            }
        });
        return userList ;
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateLoginInfo(User user){
        final String sql = "UPDATE t_user SET last_visit =?,last_ip =? ,credits=? WHERE user_id =?" ;
        jdbcTemplate.update(sql, new Object[]{user.getLastVist(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }


}
