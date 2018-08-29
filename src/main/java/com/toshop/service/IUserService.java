package com.toshop.service;

import com.toshop.common.ServerResponse;
import com.toshop.pojo.User;

public interface IUserService {

    ServerResponse<User> login(String userName, String password);

    int register(User user);


}
