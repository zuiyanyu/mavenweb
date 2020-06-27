package com.mavenweb.mapper;

import com.mavenweb.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    String getUserNameByNo(@Param("userNo") String userNo);
    User getUserByNo(@Param("userNo") String userNo);
//    Integer addUser(User user);
}
