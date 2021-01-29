package com.st.workqueues;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("Consumer_work")
public class Consumer {

    @RabbitListener(queuesToDeclare = @Queue("workqueues"))
    public void receive1(String message){
        System.out.println("receive1:"+message);
    }

    @RabbitListener(queuesToDeclare = @Queue("workqueues"))
    public void receive2(String message){
        System.out.println("receive2:"+message);
    }
}
