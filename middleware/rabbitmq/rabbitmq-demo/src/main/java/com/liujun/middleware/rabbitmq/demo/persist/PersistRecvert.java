package com.liujun.middleware.rabbitmq.demo.persist;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消息持久化的消息接收者
 *
 * <p>当消息持久化后，在重启后仍可做到消息不丢
 *
 * @author liujun
 * @version 0.0.1
 */
public class PersistRecvert {

  private static final String QUEUE_NAME = "QUEUE_NAME";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    Channel channel = connection.createChannel();
    // 定义路由模式的交换机
    // 消息持久化参数
    boolean durable = true;
    channel.exchangeDeclare(PersistSender.EXCHANGE_NAME, "direct", durable, false, null);
    channel.basicQos(1);

    // 持久化队列
    channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
    // 声明持久化队列
    channel.queueBind(QUEUE_NAME, PersistSender.EXCHANGE_NAME, PersistSender.ROUTE_NAME);

    DefaultConsumer consumer =
        new DefaultConsumer(channel) {

          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            System.out.println("收到的持久化消息:" + new String(body));
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        };
    channel.basicConsume(QUEUE_NAME, false, consumer);
  }
}
