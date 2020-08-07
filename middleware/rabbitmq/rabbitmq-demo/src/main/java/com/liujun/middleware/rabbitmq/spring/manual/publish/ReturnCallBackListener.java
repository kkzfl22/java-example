package com.liujun.middleware.rabbitmq.spring.manual.publish;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 失败回滚操作
 *
 * @author liujun
 * @version 0.0.1
 */
@Component("returnCallBackListener")
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

  @Override
  public void returnedMessage(Message message, int  i, String s, String s1, String s2) {
    System.err.println(
        "失败-message:"
            + new String(message.getBody())
            + ",replyCode:"
            + i
            + ",replyText:"
            + s
            + ",exchange:"
            + s1
            + ",routingKey:"
            + s2);
  }
}
