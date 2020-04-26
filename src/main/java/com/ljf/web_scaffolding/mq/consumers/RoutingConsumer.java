package com.ljf.web_scaffolding.mq.consumers;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by mr.lin on 2020/4/26
 * 路由模式，接收者
 */
@Component
@Slf4j
public class RoutingConsumer {

    @RabbitListener(queuesToDeclare = @Queue("orange"))//消费者声明队列
    public void queueOrange(Message msg, Channel channel) {
        log.info("queueOrange收到消息：{}", msg);
        try {
            //确认消息，multiple false 表示当前接收者收到消息，true 表示全部接收者收到消息
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
            /**
             *
             //处理消息异常，从而不确认消息， requeue 是否重回队列
             channel.basicNack(msg.getMessageProperties().getDeliveryTag(),false,true);
             //拒绝消息，requeue false 进入死信队列
             channel.basicReject(msg.getMessageProperties().getDeliveryTag(),false);
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queuesToDeclare = @Queue("black"))//消费者声明队列
    public void queueBlack(Message msg, Channel channel) {
        log.info("queueBlack收到消息：{}", msg);
    }

    @RabbitListener(queuesToDeclare = @Queue("green"))//消费者声明队列
    public void queueGreen(Message msg, Channel channel) {
        log.info("queueGreen收到消息：{}", msg);
    }

}
