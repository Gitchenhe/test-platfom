package com.chenhe.task;

import com.chenhe.common.param.MessageObject;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-04-28 17:45
 * @desc
 **/
public class QueuesPool {

    private static LinkedBlockingQueue<MessageObject> messageQueue = new LinkedBlockingQueue<>();


    public static void putMessageToQueue(MessageObject message){
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static MessageObject pollMessage(){
        try {
            return messageQueue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
