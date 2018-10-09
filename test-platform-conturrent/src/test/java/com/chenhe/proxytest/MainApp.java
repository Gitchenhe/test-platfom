package com.chenhe.proxytest;


/**
 * @author chenhe
 * @Date 2018-08-10 11:30
 * @desc
 **/
public class MainApp {

    public static void main(String[] args) {
        ProxyUtils.init();

        ProxyTestService proxyTestService = (ProxyTestService) ProxyUtils.getBean("proxyTestService");

        String resp = proxyTestService.say();
        System.out.println(resp);
    }

}
