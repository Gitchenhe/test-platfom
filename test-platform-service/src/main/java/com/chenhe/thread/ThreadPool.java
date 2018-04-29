package com.chenhe.thread;


import com.chenhe.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenhe
 * @Date 2018-04-28 16:42
 * @desc
 **/
public class ThreadPool {

    private Logger logger = LoggerFactory.getLogger(ThreadPool.class);
    /**
     * 初始化的线程
     * thread-name
     */
    protected Map<String, Integer> threadMap;

    public void initThread() {
        if (threadMap == null) {
            return;
        }
        logger.info("准备初始化线程池");
        Iterator<String> iterator = threadMap.keySet().iterator();
        while (iterator.hasNext()) {
            String threadName = iterator.next();
            Integer count = threadMap.get(threadName);
            BaseThread baseThread = (BaseThread) SpringUtils.getBean(threadName);
            ExecutorService executorService = Executors.newFixedThreadPool(count, new DefaultThreadFactory(threadName));
            for (int i =0; i < count; i++){
                Thread thread = new Thread(baseThread);
                executorService.execute(thread);
                logger.info("线程 - {} 初始化",baseThread.getThreadName());
            }
        }
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger threadNumber = new AtomicInteger(1);
        private ThreadGroup threadGroup = null;
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private String namePrefix = null;

        DefaultThreadFactory(String threadName) {
            SecurityManager securityManager = System.getSecurityManager();
            threadGroup = (securityManager != null) ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = new StringBuilder(20).append("p").append(poolNumber.getAndIncrement()).append("-").append(threadName).toString();
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(threadGroup, r, namePrefix + threadNumber.getAndIncrement(), 0);
            t.setDaemon(false);
            t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

    public Map<String, Integer> getThreadMap() {
        return threadMap;
    }

    public void setThreadMap(Map<String, Integer> threadMap) {
        this.threadMap = threadMap;
    }
}
