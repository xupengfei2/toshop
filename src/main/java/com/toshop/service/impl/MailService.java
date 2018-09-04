package com.toshop.service.impl;

import com.toshop.common.ServerResponse;
import com.toshop.dao.MailMapper;
import com.toshop.pojo.Mail;
import com.toshop.service.IMailService;
import com.toshop.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class MailService implements IMailService {


//    private MailMessage mailMessage;
//
//    private JavaMailSender simpleMailSender;

    @Resource(name = "mailUtil")
    private MailUtil mailUtil;

    private MailMapper mailMapper;

    private SimpleMailMessage simpleMailMessage;

    private Mail mail = new Mail();

    @Autowired
    public void setMailMapper(MailMapper mailMapper) {
        this.mailMapper = mailMapper;
    }

    public Mail getMail() {
        return mail;
    }

    @Override
    public ServerResponse<String> simpleMailSender(){
        try {
            mailUtil.javaMailSender().send(simpleMailMessage);
        }catch (MailException e){
            //todo 保存错误信息
            e.printStackTrace();
            return ServerResponse.createByErrorMsg("注册邮件发送失败");
        }
        mailMapper.insert(mail);
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
        //Mail mail = new Mail(username, usermail, uuid);
        simpleMailMessage = mailUtil.simpleMailMessage();
        //mailMapper.insert(mail);
        //持久化验证信息
        mail.setUsername(username);
        mail.setMail(usermail);
        mail.setUuid(uuid);
        //设置邮件内容
        StringBuilder text = new StringBuilder("亲爱的toshop用户,您在toshop的注册还差最后一步，点击如下链接（或将链接复制到浏览器中打开），完成注册。链接：\"http://localhost:8080/toshop/user/register.action?code=");
        text.append(uuid);
        text.append("&username=");
        text.append(username);
        text.append("\"");
        simpleMailMessage.setText(text.toString());
        simpleMailMessage.setTo(usermail);
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

}
