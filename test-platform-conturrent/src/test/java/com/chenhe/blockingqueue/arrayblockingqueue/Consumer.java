package com.chenhe.blockingqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author chenhe
 * @Date 2018-05-02 15:52
 * @desc
 **/
public class Consumer implements Runnable {
    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try{
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
