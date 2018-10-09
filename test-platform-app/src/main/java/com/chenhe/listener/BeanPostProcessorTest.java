package com.chenhe.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhe
 * @Date 2018-08-31 14:08
 * @desc
 **/
@Configuration
public class BeanPostProcessorTest implements BeanPostProcessor {
    Logger logger = LoggerFactory.getLogger(BeanPostProcessorTest.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
