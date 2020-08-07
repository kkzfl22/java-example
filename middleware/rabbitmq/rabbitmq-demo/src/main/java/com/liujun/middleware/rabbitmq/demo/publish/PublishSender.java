package com.liujun.middleware.rabbitmq.demo.publish;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 订阅消息的发送者
 *
 * @author liujun
 * @version 0.0.1
 */
public class PublishSender {

  /** 交换机的名称 */
  public static final String EXCHANGE_NAME = "exchange_liujun1";

  /** 场景的交换机模式 */
  public static final String EXCHANGE_MODE = "fanout";

  public static void main(String[] args) throws Exception {
    // 创建连接
    Connection connection = ConnectionUtil.getConnection();
    // 创建渠道
    Channel channel = connection.createChannel();
    // 创建交换机
    channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_MODE);

    // 将消息发送至交换机
    String msg = "这是发送至交换机的消息!";
    channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());

    channel.close();
    connection.close();
  }
}
