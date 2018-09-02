package com.toshop.service;

import com.toshop.common.ServerResponse;

public interface IMailService {

    ServerResponse<String> simpleMailSender();

    /**
     * 设置要发送给注册用户验证邮箱的相关信息
     * @param usermail 目标邮箱
     * @param username
     */
    void setRegisterMailMessage(String usermail, String username);
}
