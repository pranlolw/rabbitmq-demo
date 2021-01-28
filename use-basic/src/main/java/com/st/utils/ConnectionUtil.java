package com.st.utils;


import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

    /**
     * 创建连接rabbitmq的连接共工厂对象
     * @return
     */
    public static ConnectionFactory createConnectionFactory(){
        ConnectionFactory connectionFactory=new ConnectionFactory();
        //设置连接rabbitmq的主机
        connectionFactory.setHost("127.0.0.1");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接的虚拟主机
        connectionFactory.setVirtualHost("/demo");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        return connectionFactory;
    }
}
