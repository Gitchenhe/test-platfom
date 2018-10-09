package com.chenhe.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




/**
 * @author chenhe
 * @Date 2018-09-21 10:08
 * @desc
 **/
@Configuration
public class SenderConf {


    @Bean
    public Queue helloQueue() {
        return new Queue(MQConstant.helloQueueName);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(MQConstant.userQueueName);
    }

    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueMessage() {
        return new Queue(MQConstant.topicMessageQueueName);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(MQConstant.topicMessageQueuesName);
    }
    //===============以上是验证topic Exchange的队列==========


    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue(MQConstant.fanoutAQueueName);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(MQConstant.fanoutBQueueName);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(MQConstant.fanoutCQueueName);
    }
    //===============以上是验证Fanout Exchange的队列==========


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(MQConstant.topicExchangeName);
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(MQConstant.fanoutExchangeName);
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
