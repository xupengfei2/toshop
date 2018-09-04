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

    int checkUserEmail(String email);

    /**
     * email校验，判断 “该email存在且不属于该ID” 的数量
     * @param email
     * @param userId
     * @return int
     */
    int checkUserEmailByUserId(@Param("email") String email,
                               @Param("userId") Integer userId);

    int updateUserStateByUserName(@Param("user_state") Integer user_state,
                                  @Param("username") String username);

    int selectUserStateByUserName (String username);

    int updatePasswordByUserName(@Param("username") String username,
                                 @Param("newPassword") String newPassword);

    int checkPassword(@Param("userId") Integer userId,
                      @Param("password") String password);
}