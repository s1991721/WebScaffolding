package com.ljf.web_scaffolding.mq.producers;

import com.ljf.web_scaffolding.mq.configs.TopicExchangeConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by mr.lin on 2020/4/24
 * 主题模式，发送者
 */
@Component
public class TopicsProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendTopicMsg() {
        rabbitTemplate.convertAndSend(TopicExchangeConfig.TOPIC_EXCHANGE_NAME, TopicExchangeConfig.TOPIC_ROUTING_KEY_HEADER + "one", "one");

        rabbitTemplate.convertAndSend(TopicExchangeConfig.TOPIC_EXCHANGE_NAME, TopicExchangeConfig.TOPIC_ROUTING_KEY_HEADER + "one.two.three", "one.two.three");
    }
}
