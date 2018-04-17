package com.chenhe.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhe
 * @Date 2018-04-17 15:20
 * @desc
 **/
@Configuration
public class ChangeBeanFactory implements BeanFactoryPostProcessor {
    Logger logger =  LoggerFactory.getLogger(ChangeBeanFactory.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("== postProcessBeanFactory ==");
        //可以替换xml中的<property>标签中指定的内容
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        if (mutablePropertyValues.contains("name")){
            mutablePropertyValues.add("name","李四");
        }
    }
}
