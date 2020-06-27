package com.mavenweb.service;

import com.mavenweb.domain.User;

public interface UserService {
    String getUserName(String userNo);
    User getUserByNo(String userNo);

}
