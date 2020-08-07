package com.liujun.middleware.rabbitmq.demo.helloworld;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author liujun
 * @version 0.0.1
 */
public class RabbitMqRevert {

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();

    Channel channel = connection.createChannel();
    channel.queueDeclare(RabbitMQSender.QUEUE_NAME, false, false, false, null);

    QueueingConsumer consumer = new QueueingConsumer(channel);

    channel.basicConsume(RabbitMQSender.QUEUE_NAME, consumer);

    while (true) {
      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
      String value = new String(delivery.getBody());

      System.out.println("接收消息:"+value);
    }
  }
}
