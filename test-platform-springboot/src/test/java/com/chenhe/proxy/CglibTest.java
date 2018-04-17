package com.chenhe.proxy;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author chenhe
 * @Date 2018-04-17 16:54
 * @desc
 **/
public class CglibTest {

    public static void main(String[] args) {
        SayServiceProxy sayServiceProxy = new SayServiceProxy();
        SayService sayService = (SayService) sayServiceProxy.getProxy(SayServiceImpl.class);
        sayService.say("你好");
    }

    @Test
    public void test(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SayServiceImpl.class);
        SayServiceProxy sayServiceProxy = new SayServiceProxy();
        enhancer.setCallback(sayServiceProxy);

        SayService sayService = (SayService) enhancer.create();
        sayService.say("世界");


    }
}
