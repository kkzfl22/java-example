package com.liujun.middleware.rabbitmq.demo.persist;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

/**
 * 消息持久化的消息发送者
 *
 * @author liujun
 * @version 0.0.1
 */
public class PersistSender {

  public static final String EXCHANGE_NAME = "myexchange_name";

  public static final String ROUTE_NAME = "routeInfo";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    Channel channel = connection.createChannel();
    // 定义路由模式的交换机
    // 消息持久化参数
    boolean durable = true;
    channel.exchangeDeclare(EXCHANGE_NAME, "direct", durable, false, null);
    // 消息的发送,文件消息，并且持久化,
    String message = "这是持久化的发送消息";
    channel.basicPublish(
        EXCHANGE_NAME, ROUTE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

    channel.close();
    connection.close();
  }
}
