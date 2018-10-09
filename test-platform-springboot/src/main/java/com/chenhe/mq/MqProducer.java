package com.chenhe.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenhe
 * @Date 2018-09-21 10:17
 * @desc
 **/
@Controller
@RequestMapping("testmq")
public class MqProducer {

    @Autowired
    private AmqpTemplate template;

    @RequestMapping("send/{msg}")
    public void send(@PathVariable String msg) {
        template.convertAndSend(MQConstant.helloQueueName,msg);
    }
}
