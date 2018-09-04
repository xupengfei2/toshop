package com.toshop.controller;

import com.toshop.common.Const;
import com.toshop.common.ResponseCode;
import com.toshop.common.ServerResponse;
import com.toshop.pojo.User;
import com.toshop.service.IMailService;
import com.toshop.service.IUserService;
import com.toshop.service.impl.MailService;
import com.toshop.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Resource(type = UserService.class)
    private IUserService userService;

    @Resource(type = MailService.class)
    private IMailService mailService;

    /**
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> userLogin(String userName, String password, HttpSession session) {

        ServerResponse<User> response = userService.login(userName, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> userLogout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createByErrorMsg("登出成功");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> userRegister(User user) {
        //todo 从user中获取的数据无法显示中文
        //System.out.println("Controller:" + user.getUsername());
        ServerResponse<String> response = userService.register(user);
        //发送邮箱验证
        if (response.isSuccess()) {
            mailService.setRegisterMailMessage(user.getEmail(), user.getUsername());
            response = mailService.simpleMailSender();
            if (response.isSuccess()) {
                //验证邮件发送成功
                return response;
            } else {
                return ServerResponse.createByErrorMsg("验证邮件发送失败");
            }
        }
        return response;
    }

    @RequestMapping(value = "check_valid", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String key, String value) {
        return userService.checkValid(key, value);
    }

    /**
     * 邮箱验证，账号激活
     *
     * @param code uuid
     * @return
     */
    @RequestMapping(value = "active", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> mailActive(String code, String username) {
        return userService.activateUser(code, username);
    }

    @RequestMapping(value = "reset_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String oldPassword,
                                                String newPassword) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        return userService.resetPassword(oldPassword, newPassword, user);
    }

    @RequestMapping(value = "forget_password", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetPassword(String userName, String email){
        //todo forgetPassword
        ServerResponse<String> response = userService.forgetPassword(userName, email);
        return response;
    }

    @RequestMapping(value = "update_information", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(HttpSession session, User user) {
        User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMsg("用户未登录");
        }
        //从HttpSession中获取当前用户的ID及用户名
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerResponse<User> response = userService.updateInformation(user);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "get_information", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser ==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),
                    "未登录！需要强制登录!");
        }
        return userService.getInformation(currentUser.getId());
    }
}
