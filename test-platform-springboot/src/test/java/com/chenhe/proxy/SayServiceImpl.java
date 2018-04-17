package com.chenhe.proxy;

/**
 * @author chenhe
 * @Date 2018-04-17 16:56
 * @desc
 **/
public class SayServiceImpl implements SayService{
    @Override
    public void say(String hello) {
        System.out.println(hello);
    }
}
