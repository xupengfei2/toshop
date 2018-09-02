package com.toshop.service.impl;

import com.toshop.common.ServerResponse;
import com.toshop.dao.MailMapper;
import com.toshop.pojo.Mail;
import com.toshop.service.IMailService;
import com.toshop.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class MailService implements IMailService {


//    private MailMessage mailMessage;
//
//    private JavaMailSender simpleMailSender;

    @Resource(type = MailUtil.class)
    private MailUtil mailUtil;

    private MailMapper mailMapper;

    @Override
    public ServerResponse<String> simpleMailSender(){
        try {
            mailUtil.javaMailSender().send(mailUtil.simpleMailMessage());
        }catch (MailException e){
            //todo 保存错误信息
            return ServerResponse.createByErrorMsg("注册邮件发送失败");
        }
        return ServerResponse.createBySuccessMsg("注册邮件发送成功");
    }

    /**
     * 设置要发送给注册用户验证邮箱的相关信息
     *
     * @param usermail 目标邮箱
     */
    @Override
    public void setRegisterMailMessage(String usermail, String username) {
        //注册验证码
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        Mail mail = new Mail(username, usermail, uuid);
        mailMapper.insert(mail);
        //设置邮件内容
        StringBuilder text = new StringBuilder("<h2>亲爱的toshop用户</h2><p>您在toshop的注册还差最后一步，点击如下链接，完成注册。</p><p>链接:<a href=\"?code=");
        text.append(uuid);
        text.append("\"></a></p>");
        mailUtil.simpleMailMessage().setText(text.toString());
        mailUtil.simpleMailMessage().setTo(usermail);
    }

//    @Autowired
//    public void setMailMessage(MailMessage mailMessage) {
//        this.mailMessage = mailMessage;
//    }
//
//    @Autowired
//    public void setSimpleMailSender(JavaMailSender simpleMailSender) {
//        this.simpleMailSender = simpleMailSender;
//    }
//
    @Autowired
    public void setMailMapper(MailMapper mailMapper) {
        this.mailMapper = mailMapper;
    }
}
