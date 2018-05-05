package com.chenhe.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author chenhe
 * @Date 2018-05-04 10:18
 * @desc 信号量, 获取信号量, 如果当前信号量许可为0,线程阻塞,直到有其他线程释放足够的许可为止
 **/
public class SemaphoreTest {

    /**
     * 演示信号量使用, 信号量的许可为空时,线程阻塞
     * @param args
     */
    public static void main(String[] args){
        //初始化信号量,限制有两个许可
        Semaphore semaphore = new Semaphore(2);

        new Thread(new SemaphoreRunnable(semaphore),"线程一").start();
        new Thread(new SemaphoreRunnable(semaphore),"线程二").start();
    }

    static class SemaphoreRunnable implements Runnable{

        Semaphore semaphore;

        public SemaphoreRunnable(Semaphore semaphore){
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //先到的线程,获取全部的许可,后到的线程,在许可未全部释放前,会阻塞
                semaphore.acquire(2);
                System.out.println(Thread.currentThread().getName() +" 获取2个许可");
                Thread.sleep(3000);
                //释放一个许可, 这里可释放多个,不会有异常
                semaphore.release();
                System.out.println(Thread.currentThread().getName() +" 释放1个许可");
                semaphore.release();
                System.out.println(Thread.currentThread().getName() +" 释放1个许可");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
