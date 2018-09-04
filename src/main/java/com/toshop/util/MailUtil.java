package com.toshop.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailUtil {

    /**
     * 邮件的简单消息构造器
     * @return
     */
    @Scope("prototype")
    @Bean
    public SimpleMailMessage simpleMailMessage(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //发件人邮箱
        simpleMailMessage.setFrom("zhouricong960303@163.com");
        simpleMailMessage.setSubject("toshop网上商城注册通知");
        return simpleMailMessage;
    }


    /**
     * 简单邮件发送器
     * @return
     */
    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth","true");

        javaMailSender.setDefaultEncoding("UTF-8");
        //你的smtp服务器
        javaMailSender.setHost("smtp.163.com");
        //smtp服务器端口，默认为25
        javaMailSender.setPort(25);
        //你的邮箱名
        javaMailSender.setUsername("zhouricong960303@163.com");
        //邮箱密码,163邮箱为你的smtp、pop3授权码
        javaMailSender.setPassword("lambda1024");
        javaMailSender.setJavaMailProperties(properties);

        return javaMailSender;
    }
}
