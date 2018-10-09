package com.chenhe.proxytest;

/**
 * @author chenhe
 * @Date 2018-08-10 9:51
 * @desc
 **/

public class TestDaoImpl implements TestDao {
    @Override
    public String say(String msg) {
        System.out.println("接收到:" + msg);
        return "已经接收到:" + msg;
    }
}
