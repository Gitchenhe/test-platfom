package com.chenhe.abs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author chenhe
 * @Date 2018-09-07 14:02
 * @desc
 **/
public class DefaultQueuedSynchronizer extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        int count = getState();
        if(count > 2){
        }
        while(compareAndSetState(count,count+arg)){
            System.out.println("等待获取锁");
        }
        return true;
    }

    public static void main(String[] args){
        DefaultQueuedSynchronizer defaultQueuedSynchronizer = new DefaultQueuedSynchronizer();

        defaultQueuedSynchronizer.acquire(10);
        System.out.println(defaultQueuedSynchronizer);
        defaultQueuedSynchronizer.acquire(1);
        System.out.println(defaultQueuedSynchronizer);
        defaultQueuedSynchronizer.acquire(1);
        System.out.println(defaultQueuedSynchronizer);
        defaultQueuedSynchronizer.acquire(1);
        System.out.println(defaultQueuedSynchronizer);
    }

}
