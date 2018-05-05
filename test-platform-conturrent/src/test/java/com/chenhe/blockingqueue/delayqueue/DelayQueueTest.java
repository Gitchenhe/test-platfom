package com.chenhe.blockingqueue.delayqueue;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * @author chenhe
 * @Date 2018-05-02 16:37
 * @desc
 **/
public class DelayQueueTest {
    @Test
    public void test1() throws InterruptedException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DelayQueue queue = new DelayQueue();
        Delayed element = new DelayElement(simpleDateFormat.parse("2018-05-02 17:00:30"));
        Delayed element2 = new DelayElement(simpleDateFormat.parse("2018-05-02 17:00:30"));
        queue.add(element);
        queue.add(element2);

        element = queue.take();
        element = queue.take();

        System.out.println(element);
    }
}
