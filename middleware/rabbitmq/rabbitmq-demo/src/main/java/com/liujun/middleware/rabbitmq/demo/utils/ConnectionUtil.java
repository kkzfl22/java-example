package com.liujun.middleware.rabbitmq.demo.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 用于创建rabbitmq的相关的连接信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class ConnectionUtil {

  /**
   * 获取连接工厂
   *
   * @return
   * @throws Exception
   */
  public static Connection getConnection() throws Exception {
    // 定义连接工厂
    ConnectionFactory factory = new ConnectionFactory();
    // 设置服务器连接
    factory.setHost("localhost");
    // 设置端口
    factory.setPort(5672);
    // 设置帐号，用户名和密码
    factory.setVirtualHost("/virtualhost_lj");
    factory.setUsername("liujun");
    factory.setPassword("liujun");

    Connection connection = factory.newConnection();
    return connection;
  }
}
