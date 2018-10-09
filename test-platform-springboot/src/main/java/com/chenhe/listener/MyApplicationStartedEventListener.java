package com.chenhe.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author chenhe
 * @Date 2018-04-17 10:24
 * @desc
 **/
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    private Logger logger = LoggerFactory.getLogger(MyApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        SpringApplication application = applicationStartedEvent.getSpringApplication();
        application.setBannerMode(Banner.Mode.OFF);
        logger.info("====onApplicationEvent====");
    }
}
