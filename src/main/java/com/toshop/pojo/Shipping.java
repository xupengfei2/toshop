package com.toshop.pojo;

import java.util.Date;

public class Shipping {
    private Integer id;

    private Integer userId;

    private String reciverName;

    private String reciverPhone;

    private String reciverMobile;

    private String reciverProvince;

    private String reciverCity;

    private String reciverDistrict;

    private String reciverAddress;

    private String reciverZip;

    private Date createTime;

    private Date updateTime;

    public Shipping(Integer id, Integer userId, String reciverName, String reciverPhone, String reciverMobile, String reciverProvince, String reciverCity, String reciverDistrict, String reciverAddress, String reciverZip, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.reciverName = reciverName;
        this.reciverPhone = reciverPhone;
        this.reciverMobile = reciverMobile;
        this.reciverProvince = reciverProvince;
        this.reciverCity = reciverCity;
        this.reciverDistrict = reciverDistrict;
        this.reciverAddress = reciverAddress;
        this.reciverZip = reciverZip;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Shipping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName == null ? null : reciverName.trim();
    }

    public String getReciverPhone() {
        return reciverPhone;
    }

    public void setReciverPhone(String reciverPhone) {
        this.reciverPhone = reciverPhone == null ? null : reciverPhone.trim();
    }

    public String getReciverMobile() {
        return reciverMobile;
    }

    public void setReciverMobile(String reciverMobile) {
        this.reciverMobile = reciverMobile == null ? null : reciverMobile.trim();
    }

    public String getReciverProvince() {
        return reciverProvince;
    }

    public void setReciverProvince(String reciverProvince) {
        this.reciverProvince = reciverProvince == null ? null : reciverProvince.trim();
    }

    public String getReciverCity() {
        return reciverCity;
    }

    public void setReciverCity(String reciverCity) {
        this.reciverCity = reciverCity == null ? null : reciverCity.trim();
    }

    public String getReciverDistrict() {
        return reciverDistrict;
    }

    public void setReciverDistrict(String reciverDistrict) {
        this.reciverDistrict = reciverDistrict == null ? null : reciverDistrict.trim();
    }

    public String getReciverAddress() {
        return reciverAddress;
    }

    public void setReciverAddress(String reciverAddress) {
        this.reciverAddress = reciverAddress == null ? null : reciverAddress.trim();
    }

    public String getReciverZip() {
        return reciverZip;
    }

    public void setReciverZip(String reciverZip) {
        this.reciverZip = reciverZip == null ? null : reciverZip.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}