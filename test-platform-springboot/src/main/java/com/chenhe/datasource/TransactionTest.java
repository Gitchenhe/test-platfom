package com.chenhe.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author chenhe
 * @Date 2018-05-31 15:34
 * @desc 测试事务传播机制
 **/
public class TransactionTest {
    static Logger logger = LoggerFactory.getLogger(TransactionTest.class);

    static final String LOGGER_PREFIX = "[测试事务传播机制]";

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring/spring-datasource.xml");
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) classPathXmlApplicationContext.getBean("transactionManager");
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setName("测试事务传播机制");

        logger.info("{} 外层获取事务,传播机制:{}",LOGGER_PREFIX,transactionDefinition.getPropagationBehavior());
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

        testRequired(transactionManager);

        logger.info("{} 外层提交事务",LOGGER_PREFIX);
        transactionManager.commit(transactionStatus);

        //testMandatory(transactionManager);
    }

    private static void testRequired(DataSourceTransactionManager transactionManager) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionDefinition.setName("测试PROPAGATION_REQUIRES_NEW");
        logger.info("{} 内层获取事务,级别:{}",LOGGER_PREFIX,transactionDefinition.getPropagationBehavior());
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        try {
            DataSource dataSource = transactionManager.getDataSource();
            Connection connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into t_exception(CREATE_TIME,remark,request_no) VALUES (?,?,?)");
            preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
            preparedStatement.setString(2, "REQUIRED");
            preparedStatement.setString(3, UUID.randomUUID().toString());
            preparedStatement.executeUpdate();
            logger.info("{} 内层提交事务",LOGGER_PREFIX);
            transactionManager.commit(transactionStatus);
        } catch (SQLException e) {
            transactionManager.rollback(transactionStatus);
            e.printStackTrace();
        }
    }

    private static void testMandatory(DataSourceTransactionManager transactionManager){
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);
        transactionDefinition.setName("测试REQUIRED");
        logger.info("{} 内层获取事务,级别:{}",LOGGER_PREFIX,transactionDefinition.getPropagationBehavior());
        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
    }

}
