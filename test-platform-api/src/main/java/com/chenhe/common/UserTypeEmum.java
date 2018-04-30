package com.chenhe.common;

/**
 * @author Administrator
 * @Date 2018-04-30 14:04
 * @desc 用户类型
 **/
public enum UserTypeEmum {
    ADMIN("管理员"),
    USER("普通用户");

    private String text;

    UserTypeEmum(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
