package com.liujun.middleware.rabbitmq.spring.manual.publish;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @version 0.0.1
 */
@Service("publishService")
public class PublishService {

  @Autowired private AmqpTemplate amqpTemplate;

  public void send(String exchange, String routingKey, Object message) {
    amqpTemplate.convertAndSend(exchange, routingKey, message);
  }
}
