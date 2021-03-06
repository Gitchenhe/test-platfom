package com.chenhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author chenhe
 */
@SpringBootApplication
@ComponentScan("com.chenhe")
@ImportResource(locations = {"classpath:/spring/spring-context.xml"})
public class Application extends SpringBootServletInitializer { //tomcat启动springboot项目,必须继承SpringBootServletInitializer

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setLogStartupInfo(false);
        application.run(args);
        System.out.println("-----------------");
    }
}
