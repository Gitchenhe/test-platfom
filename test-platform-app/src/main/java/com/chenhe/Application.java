package com.chenhe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author chenhe
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer { //tomcat启动springboot项目,必须继承SpringBootServletInitializer

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
