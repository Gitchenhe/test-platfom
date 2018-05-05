package com.chenhe.blockingqueue.delayqueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author chenhe
 * @Date 2018-05-02 16:38
 * @desc
 **/
public class DelayElement implements Delayed {

    private Date executeTime;

    public DelayElement(Date date){
        executeTime = date;
    }

    /**
     * 计算当前时间到执行时间之间还有多长时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println("getDelay 被执行");
        return unit.convert(executeTime.getTime()-System.currentTimeMillis(),unit);
    }

    @Override
    public int compareTo(Delayed o) {
        System.out.println("compareTo 被执行");
        if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
            return 1;
        }else if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return "执行时间:"+simpleDateFormat.format(this.executeTime);
    }
}
