package com.liujun.middleware.rabbitmq.spring.base.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 订阅模块式的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class TopicMain {

  public static void main(String[] args) throws InterruptedException {
    AbstractApplicationContext context =
        new ClassPathXmlApplicationContext("classpath:base/topic/ApplicationtContext.xml");
    RabbitTemplate rabbitTemplateSender = context.getBean(RabbitTemplate.class);
    rabbitTemplateSender.convertAndSend("key.1231.12312", "这是topic模式发送的消息");
    Thread.sleep(1000);
    context.destroy();
  }
}
