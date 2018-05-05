package com.chenhe.blockingqueue.priorityblockingqueue;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author chenhe
 * @Date 2018-05-03 16:56
 * @desc 具有优先级的阻塞队列
 **/
public class PriorityBlockingQueueTest {

    @Test
    public void test() throws InterruptedException {
        System.out.println("PriorityBlockingQueue 里的元素必须实现Comparable接口, 元素的优先级取决于compareTo的返回值");
        PriorityBlockingQueue blockingQueue = new PriorityBlockingQueue();

        for (int i = 100; i>0;i--){
            blockingQueue.put(new QueueEntity(i));
        }

        while (blockingQueue.size() > 0){
            System.out.println(blockingQueue.take());
        }
    }
}
