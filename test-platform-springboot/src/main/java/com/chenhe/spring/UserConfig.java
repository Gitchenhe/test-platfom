package com.chenhe.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenhe
 * @Date 2018-04-17 14:26
 * @desc
 **/
@Configuration
public class UserConfig {

    @Bean
    public User user() {

        User user = new User();
        user.setNaem("陈贺");
        user.setAge(25);
        return user;
    }
}
