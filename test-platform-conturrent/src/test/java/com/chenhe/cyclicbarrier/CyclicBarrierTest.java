package com.chenhe.cyclicbarrier;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author chenhe
 * @Date 2018-05-04 9:32
 * @desc CyclicBarrier 栅栏 等待所有线程都执行到栅栏处,否则阻塞, 或者设置超时时间,超时后释放线程
 **/
public class CyclicBarrierTest {

    @Test
    public void test(){

        Runnable runnable1 = () -> {
            System.out.println("栅栏1执行");
        };
        Runnable runnable2 = () -> {
            System.out.println("栅栏2执行");
        };

        CyclicBarrier cyclicBarrier1= new CyclicBarrier(2,runnable1);
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2,runnable2);

        MyTask task1 = new MyTask(cyclicBarrier1,cyclicBarrier2);
        MyTask task2 = new MyTask(cyclicBarrier1,cyclicBarrier2);

        new Thread(task1,"task1").start();
        new Thread(task2,"task2").start();

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class MyTask implements Runnable{

        CyclicBarrier cyclicBarrier1;
        CyclicBarrier cyclicBarrier2;
        public MyTask(CyclicBarrier cyclicBarrier1,CyclicBarrier cyclicBarrier2){
            this.cyclicBarrier1 = cyclicBarrier1;
            this.cyclicBarrier2 = cyclicBarrier2;

        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" 等待所有线程执行到栅栏1");
                //阻塞等到指定线程数量,到达栅栏处, 先执行栅栏的线程,然后所有线程继续执行
                this.cyclicBarrier1.await();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" 等待所有线程执行到栅栏2");
                this.cyclicBarrier2.await();
                System.out.println(Thread.currentThread().getName()+" 执行完成!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
