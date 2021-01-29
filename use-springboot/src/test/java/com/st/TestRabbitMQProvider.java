package com.st;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UseSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQProvider {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //hello world
    @Test
    public void testSendHelloWorld(){
        rabbitTemplate.convertAndSend("helloworld","hello world");
    }
    //Work queues
    @Test
    public void testSendWorkQueues(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("workqueues","work queues"+i);
        }
    }
    //fanout
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("tfanout","","fanout模型");
    }


    //Routing
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs","troute","Routing模型");
    }

    //Topic
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","com.topic","Topic模型");
    }

}


