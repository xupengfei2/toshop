package com.toshop.controller.backend;

import com.toshop.common.Const;
import com.toshop.common.ServerResponse;
import com.toshop.pojo.User;
import com.toshop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@Controller
public class UserManagerController {

    @Autowired
    private IUserService userService;

    public ServerResponse<User> managerLogin(String userName, String password,
                                             HttpSession session){
        ServerResponse<User> response = userService.login(userName, password);
        if (response.isSuccess()){
            User user = response.getData();
            if (user.getRole() == Const.User.ROLE_ADMIN){
                return ServerResponse.createBySuccess("管理员登录成功",response.getData());
            }else{
                return ServerResponse.createByErrorMsg("对不起，您不是管理员");
            }
        }
        return response;
    }
}
