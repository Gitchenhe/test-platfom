package com.chenhe;

import com.chenhe.factorybean.db.MysqlDB;
import com.chenhe.factorybean.dto.DBEntity;
import com.chenhe.factorybean.dto.MysqlDBEntity;
import com.chenhe.listener.MyApplicationEnvironmentPreparedEventListener;
import com.chenhe.listener.MyApplicationFailedEventListener;
import com.chenhe.listener.MyApplicationPreparedEventListener;
import com.chenhe.listener.MyApplicationStartedEventListener;
import com.chenhe.spring.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author chenhe
 */
@SpringBootApplication
@ImportResource("classpath:/spring/spring.xml")
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.addListeners(new MyApplicationStartedEventListener());
		app.addListeners(new MyApplicationEnvironmentPreparedEventListener());
		app.addListeners(new MyApplicationFailedEventListener());
		app.addListeners(new MyApplicationPreparedEventListener());
		ConfigurableApplicationContext context = app.run(args);
		User user = context.getBean(User.class);
		System.out.println(user);

		MysqlDB mysqlDB = (MysqlDB) context.getBean("proxyDBObject");
		MysqlDBEntity dbEntity = new MysqlDBEntity();
		dbEntity.setId(1);
		mysqlDB.save(dbEntity);

	}
}
