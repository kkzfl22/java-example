package com.liujun.middleware.rabbitmq.spring.base.topic;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TopicListener {

  public void recvert(String message) {
    System.out.println("===========================================");
    System.err.println("topic接收消息信息:" + message);
  }
}
