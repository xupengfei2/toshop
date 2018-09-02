package com.toshop.dao;

import com.toshop.pojo.Mail;

import java.util.Date;

public interface MailMapper {

    int insert(Mail mail);

    int selectUuid(String uuid);

    int updateUuidToNull(String uuid);

    Date selectCreateTimeByUuid(String uuid);

    int selectUserNameByUuid(String uuid);

    String selectUuidByUserName(String username);

}
