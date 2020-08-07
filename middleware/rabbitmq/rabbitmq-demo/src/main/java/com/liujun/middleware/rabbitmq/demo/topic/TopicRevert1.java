package com.liujun.middleware.rabbitmq.demo.topic;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 使用topic模式的接收消息方
 *
 * <p>#表示匹配一个或者多个词
 *
 * <p>*仅能匹配一个词
 *
 * @author liujun
 * @version 0.0.1
 */
public class TopicRevert1 {

  private static final String TOPIC_REVERT_NAME = "topic_revert_queue_name";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    // 创建消息通道
    Channel channel = connection.createChannel();
    // 创建消息接收队列
    channel.queueDeclare(TOPIC_REVERT_NAME, false, false, false, null);
    // 设置消息为接收方确认
    channel.basicQos(1);

    channel.queueBind(TOPIC_REVERT_NAME, TopicSender.TOPIC_EXCHANGE_NAME, "key.*");
    channel.queueBind(TOPIC_REVERT_NAME, TopicSender.TOPIC_EXCHANGE_NAME, "abc.#");

    // 定义消息接收处理对象
    DefaultConsumer consumer =
        new DefaultConsumer(channel) {

          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {

            System.out.println("当前消息:" + new String(body));
            // 进行消息的ack确认
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        };
    // 参数2，表示是否开启手工确认机制
    channel.basicConsume(TOPIC_REVERT_NAME, false, consumer);
  }
}
