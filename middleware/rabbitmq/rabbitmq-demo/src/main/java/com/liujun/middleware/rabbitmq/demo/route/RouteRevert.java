package com.liujun.middleware.rabbitmq.demo.route;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 路由模式中的接收端
 *
 * @author liujun
 * @version 0.0.1
 */
public class RouteRevert {

  private static final String ROUTE_QUEUE_NAME = "route_queue_name1";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();
    // 创建通道
    Channel channel = connection.createChannel();
    // 声明队列
    channel.queueDeclare(ROUTE_QUEUE_NAME, false, false, false, null);
    // 绑定到交换机,并指定当前路由key
    channel.queueBind(ROUTE_QUEUE_NAME, RouteSender.EXCHANGE_ROUTE_NAME, "key1");
    // 多个类型可直接添加
    channel.queueBind(ROUTE_QUEUE_NAME, RouteSender.EXCHANGE_ROUTE_NAME, "key2");

    // 设置模式为需要接收者确认
    channel.basicQos(1);

    DefaultConsumer consumer =
        new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(
              String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
              throws IOException {
            System.out.println("当前路由模式1收到的消息:" + new String(body));
            channel.basicAck(envelope.getDeliveryTag(), false);
          }
        };

    // 参数2代表收到消息后需要手工确认
    channel.basicConsume(ROUTE_QUEUE_NAME, false, consumer);
  }
}
