package com.toshop.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String USER_NAME = "username";

    public static final String USER_EMAIL = "email";

    public interface User{
        //用户类型:0-管理员，1-普通用户
        int ROLE_COMMON = 1;
        int ROLE_ADMIN = 0;

        //用户账号状态:0-未激活，1-已激活，2-冻结
        int USER_STATE_UNACTIVE = 0;
        int USER_STATE_ACTIVE = 1;
        int USER_STATE_FREEZE = 2;
    }

    public interface Mail{
        //注册激活码有效期：24小时(毫秒)
        long TIME_MILLIS_24H = 86400000;
    }
}
