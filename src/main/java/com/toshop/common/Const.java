package com.toshop.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String USER_NAME = "username";

    public static final String USER_EMAIL = "email";

    //用户类型:0-管理员，1-普通用户
    public static final int ROLE_COMMON = 1;

    //用户账号状态:0-未激活，1-已激活，2-冻结
    //public static final int USER_STATE_UNACTIVE = 0;
    public static final int USER_STATE_ACTIVE = 1;
    public static final int USER_STATE_FREEZE = 2;

    public static final long TIME_MILLIS_24H = 86400000;
}
