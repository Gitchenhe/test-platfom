package com.chenhe.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-07-25 15:08
 * @desc
 **/
public class SynchronizedTest {

    public static void main(String[] args) {
        /*SynchronizedTest test = new SynchronizedTest();

        Runnable r1 = () -> test.method1();

        Runnable r2 = () -> method2();

        Runnable r3 = () -> test.method3();

        Thread t1 = new Thread(r1, "线程1");
        Thread t2 = new Thread(r1, "线程2");

        Thread t3 = new Thread(r2, "线程3");

        Thread t4 = new Thread(r3, "线程4");


        t1.start();
        t4.start();

        t2.start();*/

        Runnable r1 = () -> task();
        Runnable r2 = () -> task();

        new Thread(r1).start();
        new Thread(r2).start();

    }

    /**
     * 非静态方法
     */
    public synchronized void method1() {
        doTask();
    }

    /**
     * 锁静态方法
     */
    public synchronized static void method2() {
        doTask();
    }

    /**
     * 锁对象
     */
    public void method3() {
        synchronized (this) {
            doTask();
        }
    }

    private static void doTask() {
        try {
            System.out.println(Thread.currentThread().getName() + "获取锁");
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + "释放锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static int count = 0;

    private static void task() {
        int value = 10;
        if (count == 0) {
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            value++;
        }
        System.out.println("count = " + count + ", value = " + value);

    }

}
