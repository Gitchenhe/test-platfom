package com.chenhe.exchanger;


import java.util.concurrent.Exchanger;

/**
 * @author chenhe
 * @Date 2018-05-04 9:55
 * @desc 交换机, 用来表示两个线程交换对象的会合点, 交换线程必须同时到达会合点,否则先到达的线程处于阻塞状态
 *  交换是随机的: 先到达会合点的线程,与被交换的线程是随机的,取决于谁先到达会合点
 **/
public class ExchangerTest {

    /**
     * 演示线程间,对象交换
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //声明交换机
        Exchanger exchanger = new Exchanger();
        //初始四个线程
        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger,"A");
        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger,"B");
        ExchangerRunnable exchangerRunnable3 = new ExchangerRunnable(exchanger,"C");
        ExchangerRunnable exchangerRunnable4 = new ExchangerRunnable(exchanger,"D");
        //线程执行,交换机数据交换是随机的
        new Thread(exchangerRunnable1, "线程A").start();
        new Thread(exchangerRunnable2, "线程B").start();
        new Thread(exchangerRunnable3, "线程C").start();
        new Thread(exchangerRunnable4, "线程D").start();


    }

    static class ExchangerRunnable implements Runnable{

        Exchanger exchanger = null;
        Object object = null;

        public ExchangerRunnable(Exchanger exchanger,Object object){
            this.exchanger = exchanger;
            this.object = object;
        }

        @Override
        public void run() {
            Object previous = this.object;

            try {
                System.out.println(Thread.currentThread().getName() + " 到达会合点,等待被交换线程.");
                //对象交换, 被交换的对象是随机的,取决于随先到达此处
                this.object = this.exchanger.exchange(this.object);
                System.out.println(Thread.currentThread().getName() + " 用 "+previous + " 交换 " + this.object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
