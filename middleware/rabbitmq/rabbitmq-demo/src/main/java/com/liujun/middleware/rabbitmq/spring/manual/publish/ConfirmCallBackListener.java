package com.liujun.middleware.rabbitmq.spring.manual.publish;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 * @version 0.0.1
 */
@Component("confirmCallBackListener")
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    System.err.println("=====================================");
    System.err.println("确认消息:correlationData:" + correlationData + ",ack:" + ack + ",cause:" + cause);
  }
}
