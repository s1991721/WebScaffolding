package com.ljf.web_scaffolding.mq.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by mr.lin on 2020/4/24
 * 工作队列模式，发送者
 */
@Component
public class WorkProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendDirectMsg(String msg) {
        rabbitTemplate.convertAndSend("worker_queue", msg);
    }

}
