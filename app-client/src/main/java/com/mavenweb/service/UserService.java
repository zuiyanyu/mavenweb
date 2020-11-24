package com.mavenweb.service;

import com.mavenweb.dao.UserDao;
import com.mavenweb.domain.LoginLog;
import com.mavenweb.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao ;

    @Resource
    private LoginLogService loginLogService;

    public void loginSuccess(User user){

        user.setCredits(5+user.getCredits());
        userDao.updateLoginInfo(user);

        //更新日志
        LoginLog loginLog = new LoginLog();

        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVist());

        loginLogService.insertLoginLog(loginLog);
    }

    public boolean hasMatchUser(String userName,String password){
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount>0 ;
    };
     public List<User> findUserByUserName(final String userName){
         List<User> userByUserName = userDao.findUserByUserName(userName);
         return userByUserName ;
     }
    /**
     * 更新用户信息
     * @param user
     */
    public void updateLoginInfo(User user){
        userDao.updateLoginInfo(user);
    }


}
