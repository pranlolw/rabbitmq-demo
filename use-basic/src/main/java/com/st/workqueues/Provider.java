package com.st.workqueues;

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
        /**
         * 通道绑定对应消息队列
         * 参数1：队列名称，如果队列不存在，则自动创建
         * 参数2：用来定义队列特性是否要持久化，true：持久化队列，false：不持久化
         * 参数3：是否独占队列，true：独占队列，false：不独占
         * 参数4：是否在消费完成后自动删除队列，true：自动删除，false：不自动删除
         * 参数5：附加的额外参数
         */
        channel.queueDeclare("workqueues",false,false,false,null);

        /**
         * 发布消息
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：传递消息的额外设置，消息持久化：MessageProperties.PERSISTENT_TEXT_PLAIN
         * 参数4：消息的具体内容
         */
        for (int i=0;i<10;i++){
            channel.basicPublish("", "workqueues", null,("第（"+i+"）个work queues").getBytes());
        }


        //关闭通道
        channel.close();
        connection.close();
    }
}
