package com.st.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(value=@Queue,
                            exchange = @Exchange(value = "directs",type = "direct"),
                            key = {"troute","troute2"}
            )
    })
    public void receive1(String message){
        System.out.println("receive1:"+message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(value=@Queue,
                    exchange = @Exchange(value = "directs",type = "direct"),
                    key = {"troute","troute2"}
            )
    })
    public void receive2(String message){
        System.out.println("receive2:"+message);
    }
}
