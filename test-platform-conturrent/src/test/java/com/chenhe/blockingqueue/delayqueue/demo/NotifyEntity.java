package com.chenhe.blockingqueue.delayqueue.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-05-02 17:02
 * @desc
 **/
public class NotifyEntity implements Delayed {

    private Date nextExecuteTime;
    private String name;
    private int times = 1;

    public NotifyEntity(String name, Date nextExecuteTime) {
        this.name = name;
        this.nextExecuteTime = nextExecuteTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(getNextTime() - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        if (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS) > 0) {
            return 1;
        } else if (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS) < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    private long getNextTime() {
        long result;
        if (times == 1) {
            result = this.nextExecuteTime.getTime();
        } else {
            result = this.nextExecuteTime.getTime() + times * 1000;
        }
        return result;
    }

    @Override
    public String toString() {
        return "name = " + name + " produce! execute time = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getNextTime());
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
