package com.chenhe.blockingqueue.delayqueue.demo;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @author chenhe
 * @Date 2018-05-02 17:11
 * @desc
 **/
public class NotifyDemo {

    @Test
    public void startUp() throws InterruptedException {
        DelayQueue<NotifyEntity> delayQueue = new DelayQueue<>();
        for (int i = 1; i<=2; i++){
            NotifyEntity notifyEntity = new NotifyEntity("message-"+i,new Date());
            delayQueue.put(notifyEntity);
        }

        while (true){
            NotifyEntity entity = delayQueue.take();
            System.out.println("处理:并重新加入队列"+entity);
            entity.setTimes(entity.getTimes()+1);
            delayQueue.add(entity);
        }
    }


}
