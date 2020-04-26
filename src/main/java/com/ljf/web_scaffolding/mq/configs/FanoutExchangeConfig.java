package com.ljf.web_scaffolding.mq.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mr.lin on 2020/4/26
 * Fanout交换机配置
 */
@Configuration
public class FanoutExchangeConfig {

    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";
    public static final String FANOUT_QUEUE_ONE = "fanout_queue_one";
    public static final String FANOUT_QUEUE_TWO = "fanout_queue_two";

    /**
     * @param name       交换机名称.
     * @param durable    持久化，MQ重启后仍存在
     * @param autoDelete 没使用者自动删除
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME, true, false);
    }

    /**
     * @param name       队列名称
     * @param durable    持久化，MQ重启后仍存在
     * @param exclusive  独占，只能声明者可连接
     * @param autoDelete 没使用者自动删除
     */
    @Bean
    public Queue queueOne() {
        return new Queue(FANOUT_QUEUE_ONE, true, false, false);
    }

    @Bean
    public Queue queueTwo() {
        return new Queue(FANOUT_QUEUE_TWO, true, false, false);
    }

    @Bean
    public Binding bindingOne() {
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingTwo() {
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }

}
