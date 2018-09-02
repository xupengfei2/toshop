package com.toshop.service;

import com.toshop.common.ServerResponse;
import com.toshop.pojo.User;

public interface IUserService {

    ServerResponse<User> login(String userName, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String key, String value);

    ServerResponse<String> activateUser(String uuid, String username);

}
