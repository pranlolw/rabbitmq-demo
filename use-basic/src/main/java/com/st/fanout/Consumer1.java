package com.st.fanout;

import com.rabbitmq.client.*;
import com.st.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer1 {


    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = ConnectionUtil.createConnectionFactory().newConnection();
        //获取连接通道
        Channel channel = connection.createChannel();

        //通道绑定交换机
        channel.exchangeDeclare("PublishSubscribe","fanout");
        //临时队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queueName,"PublishSubscribe","");


        /**
         * 消费消息
         * 参数1：队列名称，用于指定消费哪个队列的消息
         * 参数2：开始消息的自动确认机制
         * 参数3：消费时的回调接口
         */
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            /**
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body 消息队列中取出的消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });

    }
}
