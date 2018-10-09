package com.chenhe.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhe
 * @Date 2018-04-17 14:25
 * @desc
 **/
@Configuration
public class CustomerBeanPostProcessor implements BeanPostProcessor{
    Logger logger = LoggerFactory.getLogger(CustomerBeanPostProcessor.class);
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       // logger.info("=== postProcessAfterInitialization === 当前类:{},beanName:{}",bean,beanName);
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //logger.info("=== postProcessBeforeInitialization === 当前类:{},beanName={}",bean,beanName);
        if (bean instanceof  User){
            User user = (User)bean;
            user.setNaem("张三");
        }
        return bean;
    }
}
