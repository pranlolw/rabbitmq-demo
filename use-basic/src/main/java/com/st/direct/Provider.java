package com.st.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.st.utils.ConnectionUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = ConnectionUtil.createConnectionFactory().newConnection();
        //获取连接通道
        Channel channel = connection.createChannel();

        //将通道声明指定交换机，参数1：交换机名称，参数2：交换机类型  direct：路由类型
        channel.exchangeDeclare("routing-direct", "direct");


        /**
         * 发布消息
         * 参数1：交换机名称
         * 参数2：路由名称
         * 参数3：传递消息的额外设置，消息持久化：MessageProperties.PERSISTENT_TEXT_PLAIN
         * 参数4：消息的具体内容
         */
        channel.basicPublish("routing-direct","directRoutingKey", null,("这是基于路由key：directRoutingKey发布的消息").getBytes());

        //关闭通道
        channel.close();
        connection.close();
    }
}
