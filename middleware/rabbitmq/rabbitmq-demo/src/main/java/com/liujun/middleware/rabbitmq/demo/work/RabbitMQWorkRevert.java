package com.liujun.middleware.rabbitmq.demo.work;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 通过消息队列发送消息
 *
 * @author liujun
 * @version 0.0.1
 */
public class RabbitMQWorkRevert {

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    // 获取通道
    Channel channel = connection.createChannel();
    // 获取队列
    channel.queueDeclare(RabbitMQWorkSender.QUEUE_NAME, false, false, false, null);

    // 告诉服务器，在没有确认当前消息完成之前，不要给我发新的消息
    channel.basicQos(1);

    DefaultConsumer consumer =
        new DefaultConsumer(channel) {

          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            String reverMsg = new String(body);
            System.out.println("1收到的消息:" + reverMsg);
            try {
              // 模拟任务耗时
              Thread.sleep(300);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

            // 收到消息后手动ack进行确认
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        };

    channel.basicConsume(RabbitMQWorkSender.QUEUE_NAME, false, consumer);
  }
}
