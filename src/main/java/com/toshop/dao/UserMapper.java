package com.toshop.dao;

import com.toshop.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //mybatis传入多个参数时，需要使用@Param注解
    User selectByUserNameAndPassword(@Param("username") String username,
                                     @Param("password") String password);

    int checkUserName(String username);
}