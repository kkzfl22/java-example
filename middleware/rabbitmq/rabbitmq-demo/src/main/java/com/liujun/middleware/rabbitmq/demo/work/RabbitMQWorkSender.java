package com.liujun.middleware.rabbitmq.demo.work;

import com.liujun.middleware.rabbitmq.demo.utils.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 通过消息队列发送消息
 *
 * @author liujun
 * @version 0.0.1
 */
public class RabbitMQWorkSender {

  public static final String QUEUE_NAME = "QUEUE_WORK_LIUJUN";

  public static void main(String[] args) throws Exception {
    // 获取连接及mq通道上
    Connection connection = ConnectionUtil.getConnection();
    // 从连接中创建通道
    Channel channel = connection.createChannel();
    // 声明队列
    // 参数1，队列外
    // 参数2，是否持久化,默认在内存，重启后数据丢失，
    // 参数3，exclusive是否排外，两件作用,1,当前连接关闭时队列自动删除，
    // 2，该队列是否私有，如果不排外，可以使用两个消费者访问同一个队列，没有任何问题，如果排外，会对当前队列加锁，其他channel不能访问
    // autoDelete，是否自动删除
    // arguments参数
    channel.queueDeclare("QUEUE_NAME", false, false, false, null);

    for (int i = 0; i < 100; i++) {
      // 消息内容
      String message = "Hello,world : " + i;

      // 参数1，交换机，参数2，发送到哪个队列，参数3，属性，参数4，内容
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.println("发送数据:" + message);
    }

    // 关闭通道
    channel.close();
    connection.close();
  }
}
