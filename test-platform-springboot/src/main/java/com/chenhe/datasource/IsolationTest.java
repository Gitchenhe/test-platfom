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
import java.sql.*;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author chenhe
 * @Date 2018-05-31 16:06
 * @desc 隔离机制测试
 **/
public class IsolationTest {
    static Logger logger = LoggerFactory.getLogger(IsolationTest.class);
    static final String LOGGER_PREFIX = "[事务隔离级别测试]";

    public static void main(String[] args){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath*:spring/spring-datasource.xml");
        DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) classPathXmlApplicationContext.getBean("transactionManager");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(new InsertTask(transactionManager));
        executorService.submit(new QueryTask(transactionManager));

        executorService.shutdown();
    }

    static class InsertTask implements Runnable{
        DataSourceTransactionManager dataSourceTransactionManager;
        public InsertTask(DataSourceTransactionManager dataSourceTransactionManager){
            this.dataSourceTransactionManager = dataSourceTransactionManager;
        }
        @Override
        public void run() {
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
            transactionDefinition.setName("读取未提交的更改 - insert");
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

            DataSource dataSource = dataSourceTransactionManager.getDataSource();
            Connection connection = DataSourceUtils.getConnection(dataSource);
            try{
                PreparedStatement preparedStatement = connection.prepareStatement("insert into t_exception(CREATE_TIME,remark,request_no) VALUES (?,?,?)");
                preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
                preparedStatement.setString(2, "REQUIRED");
                preparedStatement.setString(3, UUID.randomUUID().toString());
                preparedStatement.executeUpdate();

                logger.info("{} insertTask 准备休眠",LOGGER_PREFIX);
                TimeUnit.SECONDS.sleep(10);
                logger.info("{} insertTask 内层提交事务",LOGGER_PREFIX);
                dataSourceTransactionManager.commit(transactionStatus);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class QueryTask implements Runnable{

        DataSourceTransactionManager dataSourceTransactionManager;
        public QueryTask(DataSourceTransactionManager dataSourceTransactionManager){
            this.dataSourceTransactionManager = dataSourceTransactionManager;
        }

        @Override
        public void run() {
            DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
            transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
            transactionDefinition.setName("读取未提交的更改 - query");
            TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
            DataSource dataSource = dataSourceTransactionManager.getDataSource();
            Connection connection = DataSourceUtils.getConnection(dataSource);
            try {
                TimeUnit.SECONDS.sleep(2);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT max(id) from t_exception");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    logger.info("{} queryTask 查询结果 {}",LOGGER_PREFIX,resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
