package com.chenhe.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author chenhe
 * @Date 2018-04-18 9:47
 * @desc
 **/
@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }
    public static  <T> T getBean(Class<T> cls){
        return applicationContext.getBean(cls);
    }
}
