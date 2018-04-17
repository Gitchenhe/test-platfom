package com.chenhe.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @author chenhe
 * @Date 2018-04-17 16:08
 * @desc
 **/
@Component
public class Beanfactoryaware implements BeanFactoryAware{
    Logger logger = LoggerFactory.getLogger(Beanfactoryaware.class);
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("=== setBeanFactory === :{}",beanFactory );

    }
}
