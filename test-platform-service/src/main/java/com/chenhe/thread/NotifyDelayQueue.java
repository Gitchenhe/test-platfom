package com.chenhe.thread;

import java.io.Serializable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-04-28 17:46
 * @desc
 **/
public class NotifyDelayQueue implements Serializable, Delayed {

    private long id;

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
