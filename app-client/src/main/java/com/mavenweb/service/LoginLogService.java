package com.mavenweb.service;

import com.mavenweb.dao.LoginLogDao;
import com.mavenweb.domain.LoginLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginLogService {
    @Resource
    private LoginLogDao loginLogDao ;

    /**
     *
     * @param loginlog
     */
    public void insertLoginLog(LoginLog loginlog){
        loginLogDao.insertLoginLog(loginlog);
    }
}
