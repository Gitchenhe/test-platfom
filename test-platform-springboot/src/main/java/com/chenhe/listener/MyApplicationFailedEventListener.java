package com.chenhe.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author chenhe
 * @Date 2018-04-17 10:33
 * @desc
 **/
public class MyApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationFailedEventListener.class);
    @Override
    public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
        Throwable throwable = applicationFailedEvent.getException();
        logger.info("=====MyApplicationFailedEventListener====");
        throw new RuntimeException(throwable);
    }


}
