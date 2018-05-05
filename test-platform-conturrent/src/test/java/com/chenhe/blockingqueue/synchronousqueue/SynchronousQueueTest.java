package com.chenhe.blockingqueue.synchronousqueue;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author chenhe
 * @Date 2018-05-03 17:07
 * @desc SynchronousQueue只允许有一个元素
 **/
public class SynchronousQueueTest {

    @Test
    public void test() throws InterruptedException {
        BlockingQueue blockingQueue = new SynchronousQueue();
        blockingQueue.put(1);
        //阻塞
        blockingQueue.put(2);
    }
}
