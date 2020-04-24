package com.ljf.web_scaffolding.mq.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mr.lin on 2020/4/23
 * Topics交换机配置
 */
@Configuration
public class TopicExchangeConfig {

    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";
    public static final String TOPIC_QUEUE_NAME_ALL = "topic_queue_all";
    public static final String TOPIC_QUEUE_NAME_ONE = "topic_queue_one";
    public static final String TOPIC_ROUTING_KEY_HEADER = "topic_routing_key.";
    public static final String TOPIC_ROUTING_KEY_ONE = "topic_routing_key.*";
    public static final String TOPIC_ROUTING_KEY_ALL = "topic_routing_key.#";


    /**
     * @param name       交换机名称.
     * @param durable    持久化，MQ重启后仍存在
     * @param autoDelete 没使用者自动删除
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME, true, false);
    }

    /**
     * @param name       队列名称
     * @param durable    持久化，MQ重启后仍存在
     * @param exclusive  独占，只能声明者可连接
     * @param autoDelete 没使用者自动删除
     */
    @Bean
    public Queue allQueue() {
        return new Queue(TOPIC_QUEUE_NAME_ALL, true, false, false);
    }

    @Bean
    public Queue oneQueue() {
        return new Queue(TOPIC_QUEUE_NAME_ONE, true, false, false);
    }

    /**
     * 交换机与队列的绑定操作
     *
     * @return
     */
    @Bean
    public Binding allBinding() {
        return BindingBuilder.bind(allQueue()).to(topicExchange()).with(TOPIC_ROUTING_KEY_ALL);
    }

    @Bean
    public Binding oneBinding() {
        return BindingBuilder.bind(oneQueue()).to(topicExchange()).with(TOPIC_ROUTING_KEY_ONE);
    }

}
