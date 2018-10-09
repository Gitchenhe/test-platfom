package com.chenhe.task;

import com.chenhe.task.worker.BaseThread;
import com.chenhe.util.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenhe
 * @Date 2018-09-05 10:17
 * @desc
 **/
public class ThreadPool {

    Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    Map<String, Integer> threadMap = new HashMap<>();

    Set<ThreadPoolExecutor> threadPoolExecutors = new HashSet<>();



    public void initThread() throws Exception {
        for (Map.Entry<String, Integer> item : threadMap.entrySet()) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(item.getValue(), item.getValue(), 3, TimeUnit.MINUTES, new ArrayBlockingQueue<>(item.getValue()), new DefaultThreadFactory("thread-pool" + item.getKey()));
            for (int i = 0; i < item.getValue(); i++) {
                BaseThread baseThread = (BaseThread) SpringUtils.getBean(item.getKey());
                threadPoolExecutor.submit(baseThread);
            }
            threadPoolExecutors.add(threadPoolExecutor);
            logger.info("线程池{}初始化完成,数量:{}",item.getKey(),item.getValue());
        }

    }

    public void destroy() throws Exception {
        for (ThreadPoolExecutor threadPool : threadPoolExecutors){
            threadPool.shutdownNow();
        }
        logger.info("所有线程池销毁完成.");
    }


    static class DefaultThreadFactory implements ThreadFactory {

        String name;
        AtomicInteger atomicInteger = new AtomicInteger(0);

        public DefaultThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, name + "-" + atomicInteger.getAndIncrement());

        }
    }

    public void setThreadMap(Map<String, Integer> threadMap) {
        this.threadMap = threadMap;
    }
}
