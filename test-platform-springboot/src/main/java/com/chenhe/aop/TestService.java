package com.chenhe.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author chenhe
 * @Date 2018-06-04 17:34
 * @desc
 **/
@Component
public class TestService {
    private Logger logger = LoggerFactory.getLogger(TestService.class);
    private final String logPrefix = "[AOP 测试服务]";

    public void work() {
        logger.info("{} - 测试", logPrefix);
    }

    public void say(String msg) {
        logger.info("{} - 测试, msg = {}", logPrefix, msg);
    }

    public void deal(String name, int age) {
        logger.info("{} - 测试, name = {}, age = {}", logPrefix, name, age);
    }
}
