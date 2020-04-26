package com.ljf.web_scaffolding.mq.producers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by mr.lin on 2020/4/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BroadcastProducerTest {

    @Autowired
    private BroadcastProducer producer;

    @Test
    public void sendMsg() {
        for (int i = 0; i < 10; i++) {//测试用例发送多消息时，消息还未被消费者消费，测试进程就关闭了，导致log打印消息不全。
            // 开两个消费者线程，可看到正确打印
            producer.sendMsg("hello !" + i);
        }
    }
}