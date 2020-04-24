package com.ljf.web_scaffolding.mq.producers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by mr.lin on 2020/4/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicsProducerTest {

    @Autowired
    private TopicsProducer producer;

    @Test
    public void sendTopicMsg() {
        producer.sendTopicMsg();
    }

}