package com.toshop.service.impl;

import com.toshop.common.Const;
import com.toshop.common.ServerResponse;
import com.toshop.dao.MailMapper;
import com.toshop.dao.UserMapper;
import com.toshop.pojo.User;
import com.toshop.service.IUserService;
import com.toshop.util.MD5Util;
import com.toshop.util.TimeUtilBeans;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailMapper mailMapper;

    @Resource(type = TimeUtilBeans.class)
    private TimeUtilBeans timeUtilBeans;

    @Override
    public ServerResponse<User> login(String userName, String password) {

        if (userMapper.checkUserName(userName) == 0) {
            return ServerResponse.createByErrorMsg("用户名不存在");
        }
        if (userMapper.selectUserStateByUserName(userName) == 0) {
            return ServerResponse.createByErrorMsg("账号未激活,请到注册的邮箱激活");
        }
        //MD5加密的密码
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectByUserNameAndPassword(userName, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMsg("密码错误");
        }
        //密码置空(static final String "")
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", user);
    }

    @Override
    public ServerResponse<String> register(User user) {
        //用户名检查：判断username是否已存在
        ServerResponse validResponse = this.checkValid(Const.USER_NAME, user.getUsername());
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        //email检查
        validResponse = this.checkValid(Const.USER_EMAIL, user.getEmail());
        if (!validResponse.isSuccess()) {
            return validResponse;
        }
        //设置用户级别为普通用户
        user.setRole(Const.User.ROLE_COMMON);
        //设置账号状态为未激活（待邮箱验证）
        user.setState(Const.User.USER_STATE_UNACTIVE);
        //将明文密码保存为MD5加密形式的字符串
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMsg("注册失败");
        }
        return ServerResponse.createBySuccessMsg("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String key, String value) {
        //内容空判断
        if (org.apache.commons.lang3.StringUtils.isNotBlank(value)) {
            //用户名校验
            if (key.equals("username")) {
                if (userMapper.checkUserName(value) > 0) {
                    return ServerResponse.createByErrorMsg("用户名已存在");
                }
            }
            //邮箱校验
            if (key.equals("email")) {
                if (userMapper.checkUserEmail(value) > 0) {
                    return ServerResponse.createByErrorMsg("Email已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMsg("参数有误");
        }
        return ServerResponse.createBySuccessMsg("校验成功");
    }

    @Override
    public ServerResponse<String> activateUser(String uuid, String username) {

        Date createTime = mailMapper.selectCreateTimeByUuid(uuid);
        Calendar emptyCalendar = timeUtilBeans.getEmptyCalendar();
        long nowInMillis = timeUtilBeans.getSystemTimeInMillis();

        if (userMapper.checkUserName(username) == 0) {
            return ServerResponse.createByErrorMsg("用户名不存在");
        }
        //用户已被激活
        if (userMapper.selectUserStateByUserName(username) == 1) {
            return ServerResponse.createByErrorMsg("用户已被激活");
        }
        //用户未激活且激活码为 null
        if (mailMapper.selectUuidByUserName(username) == null) {
            return ServerResponse.createByErrorMsg("激活过程异常，请申请重新激活");
        }
        //验证邮件超时
        emptyCalendar.setTime(createTime);
        long createTimeInMillis = emptyCalendar.getTimeInMillis();
        if (nowInMillis - createTimeInMillis >= Const.Mail.TIME_MILLIS_24H) {
            return ServerResponse.createByErrorMsg("邮箱验证超时");
        }
        //激活用户
        mailMapper.updateUuidToNull(uuid);
        userMapper.updateUserStateByUserName(Const.User.USER_STATE_ACTIVE, username);
        return ServerResponse.createBySuccessMsg("用户激活成功");
    }

    /**
     * 在线修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param user
     * @return
     */
    @Override
    public ServerResponse<String> resetPassword(String oldPassword, String newPassword,
                                                User user) {
        String md5Password = MD5Util.MD5EncodeUtf8(oldPassword);
        int resultCount = userMapper.checkPassword(user.getId(), md5Password);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMsg("旧密码错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(newPassword));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("密码更新成功");
        }
        return ServerResponse.createByErrorMsg("密码更新失败");
    }

    @Override
    public ServerResponse<String> forgetPassword(String userName, String email){
        //todo Userservice：通过邮箱找回密码
        return null;
    }

    @Override
    public ServerResponse<User> updateInformation(User user) {
        //检查邮箱是否已被使用
        int resultCount = userMapper.checkUserEmailByUserId(user.getEmail(),user.getId());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMsg("email已被使用");
        }
        User updateUser = new User();
        updateUser.setEmail(user.getEmail());
        updateUser.setPhoto(user.getPhoto());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if (updateCount > 0) {
            return ServerResponse.createBySuccess("用户信息更新成功", updateUser);
        }
        return ServerResponse.createByErrorMsg("用户信息更新失败");
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByErrorMsg("找不到当前用户");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccessData(user);

    }

}
