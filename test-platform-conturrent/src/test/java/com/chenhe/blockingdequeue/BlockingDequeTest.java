package com.chenhe.blockingdequeue;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author chenhe
 * @Date 2018-05-03 17:43
 * @desc 双端阻塞队列,可以从两端获取元素,put,达到上限后阻塞,为空时,take阻塞
 **/
public class BlockingDequeTest {

    public static void main(String[] args) throws InterruptedException {

        BlockingDeque blockingDeque = new LinkedBlockingDeque(10);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(new Consumer(blockingDeque));
        executorService.execute(new Producer(blockingDeque));

        Thread.sleep(4000);

        //executorService.shutdownNow();
    }

    static class Consumer implements Runnable{
        BlockingDeque blockingDeque;

        public Consumer(BlockingDeque blockingDeque){
            this.blockingDeque = blockingDeque;
        }
        @Override
        public void run() {
            while (true){
                try {
                    blockingDeque.putFirst(new Date());
                    System.out.println("put - queue size:"+blockingDeque.size());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer implements Runnable{

        BlockingDeque blockingDeque;

        public Producer(BlockingDeque blockingDeque){
            this.blockingDeque = blockingDeque;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Date date = (Date) blockingDeque.takeLast();
                    System.out.println("take : "+date+", queue size="+blockingDeque.size());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
