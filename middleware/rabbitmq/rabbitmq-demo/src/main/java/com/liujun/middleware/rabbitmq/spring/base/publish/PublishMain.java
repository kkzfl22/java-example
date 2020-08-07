package com.liujun.middleware.rabbitmq.spring.base.publish;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 订阅模块式的测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class PublishMain {

  public static void main(String[] args) throws InterruptedException {
    AbstractApplicationContext context =
        new ClassPathXmlApplicationContext("classpath:base/publish/ApplicationtContext.xml");
    RabbitTemplate rabbitTemplateSender = context.getBean(RabbitTemplate.class);
    rabbitTemplateSender.convertAndSend("spring hello world!");
    Thread.sleep(1000);
    context.destroy();
  }
}
