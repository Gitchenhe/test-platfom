package com.chenhe.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author chenhe
 * @Date 2018-05-29 17:24
 * @desc
 **/
public class DataSourceTest {
    static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
    public static void main(String[] args) throws SQLException, InterruptedException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring/spring.xml");
        BasicDataSource dataSource = (BasicDataSource)classPathXmlApplicationContext.getBean("dataSource");

        logger.info("获取连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());

        Connection connection1 = dataSource.getConnection();
        logger.info("获取第1个连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());

        Connection connection2 = dataSource.getConnection();
        logger.info("获取第2个连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());

        connection2.close();
        //logger.info("获取第3个连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());
        //dataSource.getConnection();

        logger.info("获取第4个连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());
       // dataSource.getConnection();

        Thread.sleep(5000);

        logger.info("获取第4个连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());
    }
}
