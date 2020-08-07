package com.liujun.middleware.rabbitmq.demo.publish;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 订阅模式的接收端
 *
 * @author liujun
 * @version 0.0.1
 */
public class PublishRevert2 {

  public static final String QUEUE_NAME = "publish_queue_liujun2";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    // 创建通道
    Channel channel = connection.createChannel();
    // 定义通道
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    // 绑定到交换机
    channel.queueBind(QUEUE_NAME, PublishSender.EXCHANGE_NAME, "");
    // 设置当前模式 ,设置当前模式为需要当前发送确认消息
    channel.basicQos(1);

    DefaultConsumer consumer =
        new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            System.out.println("当前订阅模式2：" + new String(body));
            // 发送消息确认
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        };

    // 将消息接收处理器绑定到通道中，参数2代表收到消息后需要手工确认
    channel.basicConsume(QUEUE_NAME, false, consumer);
  }
}
