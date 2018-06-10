package com.chenhe.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

/**
 * @author chenhe
 * @Date 2018-05-29 17:24
 * @desc
 **/
public class DataSourceTest {
    static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

    public static void main(String[] args) {
        //testConnectPool();
        testTransaction();

    }

    /**
     * 事务回滚测试
     * @throws SQLException
     */
    private static void testTransaction() {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring/spring-datasource.xml");
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) classPathXmlApplicationContext.getBean("transactionManager");
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setName("test-propagation");
        logger.info("[事务传播机制测试] 获取事务.");
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            DataSource dataSource = transactionManager.getDataSource();

            logger.info("[事务传播机制测试] 获取数据库连接.");
            Connection connection = DataSourceUtils.getConnection(dataSource);

            logger.info("[事务传播机制测试] 执行SQL.");
            insertWithException(connection, false);
            insertWithException(connection, true);
            transactionManager.commit(transactionStatus);
        } catch (Exception e) {
            logger.info("[事务传播机制测试] 业务出现异常");
            transactionManager.rollback(transactionStatus);
        }

    }

    private static void insertWithException(Connection connection, boolean exception) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into t_exception(CREATE_TIME,remark,request_no) VALUES (?,?,?)");
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setString(2, "事务测试");
            preparedStatement.setString(3, UUID.randomUUID().toString());

            preparedStatement.executeUpdate();
            if (exception) {
                throw new RuntimeException("发生异常");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void testConnectPool() throws SQLException, InterruptedException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring/spring-datasource.xml");
        BasicDataSource dataSource = (BasicDataSource) classPathXmlApplicationContext.getBean("dataSource");
        logger.info("获取连接前,numberActive={},numIdle={},maxTotal={}", dataSource.getNumActive(), dataSource.getNumIdle(), dataSource.getMaxTotal());

        Connection connection1 = dataSource.getConnection();
        logger.info("获取第1个连接前,numberActive={},numIdle={},maxTotal={}", dataSource.getNumActive(), dataSource.getNumIdle(), dataSource.getMaxTotal());

        Connection connection2 = dataSource.getConnection();
        logger.info("获取第2个连接前,numberActive={},numIdle={},maxTotal={}", dataSource.getNumActive(), dataSource.getNumIdle(), dataSource.getMaxTotal());

        connection2.close();
        //logger.info("获取第3个连接前,numberActive={},numIdle={},maxTotal={}",dataSource.getNumActive(),dataSource.getNumIdle(),dataSource.getMaxTotal());
        //dataSource.getConnection();

        logger.info("获取第4个连接前,numberActive={},numIdle={},maxTotal={}", dataSource.getNumActive(), dataSource.getNumIdle(), dataSource.getMaxTotal());
        // dataSource.getConnection();

        Thread.sleep(5000);

        logger.info("获取第4个连接前,numberActive={},numIdle={},maxTotal={}", dataSource.getNumActive(), dataSource.getNumIdle(), dataSource.getMaxTotal());
    }
}
