package com.ljf.web_scaffolding.mq.producers;

import com.ljf.web_scaffolding.mq.configs.FanoutExchangeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by mr.lin on 2020/4/26
 * 发布订阅模式，发送者
 */
@Component
public class BroadcastProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg){
        rabbitTemplate.convertAndSend(FanoutExchangeConfig.FANOUT_EXCHANGE_NAME,"",msg);
    }

}
