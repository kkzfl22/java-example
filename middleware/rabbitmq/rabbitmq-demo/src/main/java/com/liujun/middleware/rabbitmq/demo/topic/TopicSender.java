package com.liujun.middleware.rabbitmq.demo.topic;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 使用前缀模式的消息匹配
 *
 * @author liujun
 * @version 0.0.1
 */
public class TopicSender {

  public static final String TOPIC_EXCHANGE_NAME = "topic_exchange_name";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();

    // 获取通道
    Channel channel = connection.createChannel();

    // 创建交换机并且模式为topic
    channel.exchangeDeclare(TOPIC_EXCHANGE_NAME, "topic");

    // 1,参数1，交换机
    // 参数2，发送匹配的key
    // 参数3，null,
    // 发送的内容
    String key = "vs.adfas123##.12312";
    String message = key + "-这是发送的消息信息;";
    channel.basicPublish(TOPIC_EXCHANGE_NAME, key, null, message.getBytes());

    channel.close();
    connection.close();
  }
}
