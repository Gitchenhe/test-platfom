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
 * @desc Spring IoC容器允许BeanFactoryPostProcessor在容器实例化任何bean之前读取bean的定义(配置元数据),
 * 并可以修改它. 同时可以定义多个BeanFactoryPostProcessor,通过设置order属性来确定各个BeanFactoryPostProcessor执行顺序/
 **/

@Configuration
public class ChangeBeanFactory implements BeanFactoryPostProcessor {
    Logger logger =  LoggerFactory.getLogger(ChangeBeanFactory.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("=========== 调用了BeanFactoryPostProcessor =============");
        //可以替换xml中的<property>标签中指定的内容
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("user");
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        if (mutablePropertyValues.contains("name")){
            logger.info("==========[ 修改 bean = user 的 name = 李四 ]=============");
            mutablePropertyValues.add("name","李四");
        }
    }
}
