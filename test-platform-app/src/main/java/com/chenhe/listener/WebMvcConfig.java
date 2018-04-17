package com.chenhe.listener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * web mvc 配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将/statics/**下的访问都映射到classpath:/static/路径下
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    }

}
