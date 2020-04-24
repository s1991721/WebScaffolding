package com.ljf.web_scaffolding.mq.consumers;

import com.ljf.web_scaffolding.mq.configs.TopicExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by mr.lin on 2020/4/24
 * 主题模式，接收者
 */
@Component
@Slf4j
public class TopicsConsumer {

    @RabbitListener(queues = TopicExchangeConfig.TOPIC_QUEUE_NAME_ALL)
    public void receiveMsgFromTopicAll(String msg) {
        log.info("收到RabbitMQ来自{}的消息：{}", TopicExchangeConfig.TOPIC_QUEUE_NAME_ALL, msg);
    }

    @RabbitListener(queues = TopicExchangeConfig.TOPIC_QUEUE_NAME_ONE)
    public void receiveMsgFromTopicOne(String msg) {
        log.info("收到RabbitMQ来自{}的消息：{}", TopicExchangeConfig.TOPIC_QUEUE_NAME_ONE, msg);
    }

}
