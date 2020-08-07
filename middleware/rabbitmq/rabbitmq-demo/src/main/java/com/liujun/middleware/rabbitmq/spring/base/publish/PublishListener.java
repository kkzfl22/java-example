package com.liujun.middleware.rabbitmq.spring.base.publish;

/**
 * @author liujun
 * @version 0.0.1
 */
public class PublishListener {

  public void recvert(String message) {
    System.out.println("===========================================");
    System.err.println("接收消息信息:" + message);
  }
}
