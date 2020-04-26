package com.ljf.web_scaffolding.mq.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by mr.lin on 2020/4/26
 * 路由模式，发送者
 */
@Component
public class RoutingProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend("orange", msg);
        rabbitTemplate.convertAndSend("black", msg);
        rabbitTemplate.convertAndSend("green", msg);
    }

}
