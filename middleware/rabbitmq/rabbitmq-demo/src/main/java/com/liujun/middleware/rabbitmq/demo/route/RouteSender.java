package com.liujun.middleware.rabbitmq.demo.route;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式的发送者
 *
 * @author liujun
 * @version 0.0.1
 */
public class RouteSender {

  /** 路由模式的交换机名称 */
  public static final String EXCHANGE_ROUTE_NAME = "exchange_route_name";

  public static void main(String[] args) throws Exception {
    Connection connection = ConnectionUtil.getConnection();

    // 创建通道
    Channel channel = connection.createChannel();

    // 定义路由模式的交换机
    channel.exchangeDeclare(EXCHANGE_ROUTE_NAME, "direct");

    // 向路由模式的交换机中发送消息
    String routeKey = "key3";
    String sendMsg = routeKey + "这是路由模式的发送消息";
    channel.basicPublish(EXCHANGE_ROUTE_NAME, routeKey, null, sendMsg.getBytes());

    channel.close();
    connection.close();
  }
}
