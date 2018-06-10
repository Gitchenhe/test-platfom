package com.chenhe.redis;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author chenhe
 * @Date 2018-06-05 14:34
 * @desc 模拟分布式锁
 **/
public class DistributedLock {
    static Logger logger = LoggerFactory.getLogger(DistributedLock.class);

    JedisSentinelPool jedisSentinelPool;
    DataSourceTransactionManager transactionManager;

    private int count = 100;


    public static void main(String[] args) throws InterruptedException {



        DistributedLock lock = new DistributedLock();
      /*  ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/spring-cache.xml");
        ClassPathXmlApplicationContext context1 = new ClassPathXmlApplicationContext("classpath*:spring/spring-datasource.xml");
        lock.transactionManager = (DataSourceTransactionManager) context1.getBean("transactionManager");

        lock.jedisSentinelPool = (JedisSentinelPool) context.getBean("jedisSentinelPool");*/
        //Runnable runnable = () -> seckill();


        ExecutorService executorService = new ThreadPoolExecutor(150, 150, 0, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1));
        CountDownLatch countDownLatch = new CountDownLatch(150);
        Count count = new Count();
        for (int i = 0; i < 150; i++) {
            new Thread(new Task(count,countDownLatch)).start();
        }
        countDownLatch.await();
        System.out.println(count.getNum());
        executorService.shutdown();
    }

    public String lockWithTimeOut(String lockName, long acquireTimeOut, long timeOut) {
        Jedis jedis = null;
        String retIdentifier = null;
        try {
            jedis = jedisSentinelPool.getResource();
            String identifier = UUID.randomUUID().toString();
            String lockKey = "lock:" + lockName;
            //超时时间,上锁后超过此时间则自动释放锁
            int lockExpire = (int) (timeOut / 1000);
            //获取锁的超时时间,超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeOut;

            logger.debug("[分布式锁测试] 准备加锁,lockKey = {}, identifier = {}", lockKey, identifier);
            while (System.currentTimeMillis() < end) {

                if (jedis.setnx(lockKey, identifier) == 1) {
                    logger.info("[分布式锁测试] 成功获取锁 ---- 1");
                    jedis.expire(lockKey, lockExpire);
                    //返回随机value,用于释放锁时间确认
                    retIdentifier = identifier;
                    return retIdentifier;
                }
                //返回-1代表key没有设置超时时间,为key设置一个超时时间
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    logger.error("[分布式锁测试] 线程休眠异常.");
                    Thread.currentThread().interrupt();
                }

            }
        } finally {
            IOUtils.closeQuietly(jedis);
        }
        return retIdentifier;
    }

    public boolean releaseLock(String lockName, String identifier) {
        Jedis jedis = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            jedis = jedisSentinelPool.getResource();
            while (true) {
                logger.debug("[分布式锁测试] 尝试释放锁, lockKey = {}, identifier = {}", lockKey, identifier);
                jedis.watch(lockKey);

                if (identifier.equalsIgnoreCase(jedis.get(lockKey))) {
                    logger.info("[分布式锁测试] 释放锁 ---- 3");
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    List<Object> results = transaction.exec();
                    if (results == null) {
                        continue;
                    }
                    retFlag = true;
                }
                jedis.unwatch();
                break;
            }
        } finally {
            IOUtils.closeQuietly(jedis);
        }
        return retFlag;
    }
}
