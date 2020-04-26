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
public class RoutingProducerTest {

    @Autowired
    private RoutingProducer producer;

    @Test
    public void sendMsg() {
        producer.sendMsg("hello !");
    }
}