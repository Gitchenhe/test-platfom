package com.chenhe.aop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author chenhe
 * @Date 2018-06-04 17:49
 * @desc
 **/
public class AopMain {

    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring/spring-aop.xml");
        TestService testService = ctx.getBean(TestService.class);

        testService.work();
        testService.say("你好");
        testService.deal("陈贺",25);
    }
}
