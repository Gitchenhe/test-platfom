package com.chenhe.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;


/**
 * @author chenhe
 * @Date 2018-04-17 14:37
 * @desc
 **/
@Configuration
public class ChangeConfig implements BeanPostProcessor {
    private Logger logger = LoggerFactory.getLogger(ChangeConfig.class);

    @Value("${test-platform.env}")
    String env;

    boolean flag = true;

    public ChangeConfig(){

    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //根据开发环境和其他环境修改一些配置,如数据源
        if (flag){
            logger.info("当前环境是:{}",env);
            flag = false;
        }
        if (bean instanceof User){
            User user = (User) bean;
            user.setName("张三");
            logger.info("==== postProcessBeforeInitialization === {}",user);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
