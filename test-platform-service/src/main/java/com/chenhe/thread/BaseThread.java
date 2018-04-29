package com.chenhe.thread;

import java.util.Date;

/**
 * @author chenhe
 * @Date 2018-04-28 16:40
 * @desc
 **/
public abstract class BaseThread implements Runnable {

    protected boolean running = false;
    protected Date startDate;
    protected Date lastRunningDate;
    protected String threadName;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastRunningDate() {
        return lastRunningDate;
    }

    public void setLastRunningDate(Date lastRunningDate) {
        this.lastRunningDate = lastRunningDate;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
