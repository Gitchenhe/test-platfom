package com.chenhe.common;

/**
 * @author Administrator
 * @Date 2018-04-30 13:54
 * @desc 用户状态枚举
 **/
public enum UserStatusEnum {
    INIT("初始化"),
    NORMAL("正常"),
    FREEZE("冻结"),
    LOGOUT("注销");
    String text;

    UserStatusEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
