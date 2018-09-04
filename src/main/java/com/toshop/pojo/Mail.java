package com.toshop.pojo;

import java.util.Date;

public class Mail {

    private String username;

    private String mail;

    private String uuid;

    private Date createTime;

    private Date updateTime;

    public Mail(){

    }

    public Mail(String username, String mail, String uuid) {
        this.username = username;
        this.mail = mail;
        this.uuid = uuid;
    }

    public Mail(String username, String mail, String uuid, Date createTime, Date updateTime) {
        this.username = username;
        this.mail = mail;
        this.uuid = uuid;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
