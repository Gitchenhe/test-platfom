package com.chenhe.redis;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-06-05 17:10
 * @desc
 **/
public class Task implements Runnable{
    private Count count;
    CountDownLatch countDownLatch;

    public Task(Count count, CountDownLatch countDownLatch){
        this.count = count;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public  void run() {
        synchronized (count){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.count();
            countDownLatch.countDown();
        }
    }
}
