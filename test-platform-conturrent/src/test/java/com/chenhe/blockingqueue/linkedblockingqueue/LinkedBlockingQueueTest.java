package com.chenhe.blockingqueue.linkedblockingqueue;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chenhe
 * @Date 2018-05-02 17:36
 * @desc  LinkedBlockingQueue 适用于单一消费者的场景
 **/
public class LinkedBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        BlockingQueue blockingQueue = new LinkedBlockingQueue(300);
        for (int i =1;i<100; i++){
            blockingQueue.put(i);
        }

        while (blockingQueue.size()>0){
            System.out.println(blockingQueue.take());
        }
    }

    @Test
    public void test2(){
        int a = 8;
        int b =4;
    }
}
