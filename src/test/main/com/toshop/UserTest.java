package main.com.toshop;

import com.toshop.util.MD5Util;
import com.toshop.util.MailUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserTest {

    @Test
    public void dateTest() throws ParseException {
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
        //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        //获取系统时区的当前时间
        Calendar calendarBefter = Calendar.getInstance(TimeZone.getDefault());
        String dateBefter = "2018-09-01 12:39:34";
        //public final void setTime(Date date)
        calendarBefter.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse(dateBefter));
        //毫秒数：以1970.1.1为起始点
        long l = calendarBefter.getTimeInMillis();
        long now = System.currentTimeMillis();
        System.out.println("注册时间：" + l + "-------系统时间：" + now);
        long count = now - l;
        System.out.println("时间差：" + count);
        if (count > 86400000) {
            System.out.println("验证信息已过期");
        }

    }

    @Test
    public void md5Test(){
        String md5password = MD5Util.MD5EncodeUtf8("123456");
        System.out.println("password:\"123456\"的MD5密码：" + md5password);
    }


    @Test
    public void mailSenderTest(){
//        ApplicationContext context = new
//                ClassPathXmlApplicationContext("applicationContext.xml");
//        MailUtil mailUtil = (MailUtil) context.getBean("mailUtil");
//        SimpleMailMessage simpleMailMessage = mailUtil.simpleMailMessage();
//        simpleMailMessage.setTo("xxxxxxx@qq.com");
//        simpleMailMessage.setText("email验证测试");
//        JavaMailSenderImpl javaMailSender = mailUtil.javaMailSender();
//        javaMailSender.send(simpleMailMessage);
    }

}
