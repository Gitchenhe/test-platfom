package com.chenhe.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenhe
 * @Date 2018-09-21 10:10
 * @desc
 **/
@Component
public class MqConsumer {

    Logger logger = LoggerFactory.getLogger(MqConsumer.class);

    @RabbitListener(queues = MQConstant.helloQueueName)
    public void process(String str) {
        logger.info("{} 接收到:{}",MQConstant.helloQueueName, str);
    }

    @RabbitListener(queues = MQConstant.userQueueName)
    public void processB(String str) {
        logger.info("{} 接收到:{}",MQConstant.userQueueName, str);
    }

    @RabbitListener(queues = MQConstant.topicMessageQueueName)
    public void processC(String str) {
        logger.info("{} 接收到:{}",MQConstant.topicMessageQueueName, str);
    }

    @RabbitListener(queues = MQConstant.topicMessageQueuesName)
    public void processD(String str) {
        logger.info("{} 接收到:{}",MQConstant.topicMessageQueuesName, str);
    }

    @RabbitListener(queues = MQConstant.fanoutAQueueName)
    public void processE(String str) {
        logger.info("{} 接收到:{}",MQConstant.fanoutAQueueName, str);
    }

    @RabbitListener(queues = MQConstant.fanoutBQueueName)
    public void processF(String str) {
        logger.info("{} 接收到:{}",MQConstant.fanoutBQueueName, str);
    }

    @RabbitListener(queues = MQConstant.fanoutCQueueName)
    public void processG(String str) {
        logger.info("{} 接收到:{}",MQConstant.fanoutCQueueName, str);
    }
}
