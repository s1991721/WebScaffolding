package com.ljf.web_scaffolding.mq.consumers;

import com.ljf.web_scaffolding.mq.configs.FanoutExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by mr.lin on 2020/4/26
 * 工作队列模式，接收者
 */
@Component
@Slf4j
public class BroadcastConsumer {

    @RabbitListener(queuesToDeclare = @Queue(FanoutExchangeConfig.FANOUT_QUEUE_ONE))//消费者声明队列
    public void queueOne(String msg) {
        log.info("queueOne收到来自{}的消息:{}", FanoutExchangeConfig.FANOUT_QUEUE_ONE, msg);
    }

    @RabbitListener(queuesToDeclare = @Queue(FanoutExchangeConfig.FANOUT_QUEUE_TWO))//消费者声明队列
    public void queueTwo(String msg) {
        log.info("queueTwo收到来自{}的消息:{}", FanoutExchangeConfig.FANOUT_QUEUE_TWO, msg);
    }

}
