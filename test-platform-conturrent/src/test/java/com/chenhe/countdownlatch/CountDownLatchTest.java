package com.chenhe.countdownlatch;

import org.junit.internal.runners.statements.Fail;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenhe
 * @Date 2018-05-03 18:06
 * @desc
 **/
public class CountDownLatchTest {


    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new MyTask(countDownLatch,lock));
        executorService.execute(new MyTask(countDownLatch,lock));
        executorService.execute(new MyTask(countDownLatch,lock));


        System.out.println("wait all task finish");
        countDownLatch.await();
        System.out.println("all task finished!");

        executorService.shutdown();
    }

    static class MyTask implements Runnable {
        CountDownLatch countDownLatch;
        Lock lock;
        public MyTask(CountDownLatch countDownLatch,Lock lock) {
            this.countDownLatch = countDownLatch;
            this.lock = lock;
        }

        @Override
        public void run() {
            boolean isRun = true;
            while (isRun) {

                lock.lock();
                countDownLatch.countDown();
                long count = countDownLatch.getCount();

                isRun =count > 1 ? true : false;
                System.out.println("do some thing ..." + count);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        }
    }
}
