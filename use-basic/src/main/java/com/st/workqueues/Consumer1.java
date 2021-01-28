package com.st.workqueues;

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
        /**
         * 通道绑定对应消息队列
         * 参数1：队列名称，如果队列不存在，则自动创建
         * 参数2：用来定义队列特性是否要持久化，true：持久化队列，false：不持久化
         * 参数3：是否独占队列，true：独占队列，false：不独占
         * 参数4：是否在消费完成后自动删除队列，true：自动删除，false：不自动删除
         * 参数5：附加的额外参数
         */
        channel.queueDeclare("workqueues",false,false,false,null);


        channel.basicQos(1);//设置为每次指消费一条消息
        /**
         * 消费消息
         * 参数1：队列名称，用于指定消费哪个队列的消息
         * 参数2：开始消息的自动确认机制，设置为false,表示手动ack，返回消息确认
         * 参数3：消费时的回调接口
         */
        channel.basicConsume("workqueues",false,new DefaultConsumer(channel){
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
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(new String(body));
                //手动返回消息确认，参数1：手动确认的是哪个消息标识，参数2：fasle 每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
