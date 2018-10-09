package com.chenhe.task.worker;


import com.chenhe.common.param.MessageObject;
import com.chenhe.task.QueuesPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author chenhe
 * @Date 2018-09-05 10:26
 * @desc
 **/
public class MessageReadWorker extends BaseThread {

    Logger logger = LoggerFactory.getLogger(MessageReadWorker.class);

    @Override
    public void run() {
        logger.info("{} 开始执行", Thread.currentThread().getName());
        startDate = new Date();

        running = true;
        while (running){
            MessageObject messageObject = QueuesPool.pollMessage();
            if (messageObject == null){
                //commit transaction
            }
        }
    }
}
