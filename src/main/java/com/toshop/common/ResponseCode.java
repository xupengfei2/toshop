package com.toshop.common;

/**
 * 服务器响应数据的状态枚举类
 * status - message
 * 0 - SUCCESS
 * 1 - ERROR
 * 2 - ILLEGAL_ARGUMENT
 * 10 - NEED_LOGIN
 */
public enum  ResponseCode {

    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT"),
    NEED_LOGIN(10,"NEED_LOGIN");

    private int code;
    private String desc;

    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
