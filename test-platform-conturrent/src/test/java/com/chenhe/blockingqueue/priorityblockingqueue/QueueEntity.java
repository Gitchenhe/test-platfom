package com.chenhe.blockingqueue.priorityblockingqueue;

/**
 * @author chenhe
 * @Date 2018-05-03 16:56
 * @desc
 **/
public class QueueEntity implements Comparable {
    private int id;

    public QueueEntity(int id){
        this.id = id;
    }
    @Override
    public int compareTo(Object o) {
        QueueEntity entity = (QueueEntity) o;
        if (this.id > entity.getId()) {
            return 1;
        } else if (this.id < entity.getId()) {
            return -1;
        } else {
            return 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id = " + id;
    }
}
