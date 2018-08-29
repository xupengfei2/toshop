package com.toshop.service.impl;

import com.toshop.common.ServerResponse;
import com.toshop.dao.UserMapper;
import com.toshop.pojo.User;
import com.toshop.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String userName, String password) {
        if (userMapper.checkUserName(userName) == 0){
            return ServerResponse.createByErrorMsg("用户名不存在");
        }
        User user = userMapper.selectByUserNameAndPassword(userName,password);
        //todo MD5加密
        if (user == null){
            return ServerResponse.createByErrorMsg("密码错误");
        }
        //密码置空(static final String "")
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public int register(User user) {
        return 0;
    }
}
