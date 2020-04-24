package com.ljf.web_scaffolding.mq.producers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mr.lin on 2020/4/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkProducerTest {

    @Autowired
    private WorkProducer producer;

    @Test
    public void sendDirectMsg() {
        for (int i = 0; i < 10; i++) {
            producer.sendDirectMsg("hello !" + i);
        }
    }

}