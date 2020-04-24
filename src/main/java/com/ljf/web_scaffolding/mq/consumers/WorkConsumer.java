package com.ljf.web_scaffolding.mq.consumers;

import com.ljf.web_scaffolding.mq.configs.DirectExchangeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by mr.lin on 2020/4/24
 * 工作队列模式，接收者
 */
@Component
@Slf4j
//@RabbitListener 可注解到类上，类内的方法需要使用@RabbitHandler注解
public class WorkConsumer {

//    @RabbitHandler 根据传入消息的类型，选择要处理消息的注解方法

    @RabbitListener(queuesToDeclare = @Queue("worker_queue"))//消费者声明队列
    public void workerOne(String msg) {
        log.info("workerOne收到来自{}的消息：{}", DirectExchangeConfig.DIRECT_QUEUE_NAME, msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("worker_queue"))//消费者声明队列
    public void workerTwo(String msg) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("workerTwo收到来自{}的消息：{}", DirectExchangeConfig.DIRECT_QUEUE_NAME, msg);
    }

}
