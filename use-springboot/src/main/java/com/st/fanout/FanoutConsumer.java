package com.st.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时交换机
                    exchange = @Exchange(value = "tfanout",type = "fanout")//绑定交换机

            )
    })
    public void receive1(String message){
        System.out.println("receive1:"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//创建临时交换机
                    exchange = @Exchange(value = "tfanout",type = "fanout")//绑定交换机

            )
    })
    public void receive2(String message){
        System.out.println("receive2:"+message);
    }
}
