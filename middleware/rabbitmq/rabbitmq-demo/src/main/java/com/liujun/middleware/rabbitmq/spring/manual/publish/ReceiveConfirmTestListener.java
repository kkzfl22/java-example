package com.liujun.middleware.rabbitmq.spring.manual.publish;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 消息消息者
 *
 * @author liujun
 * @version 0.0.1
 */
@Component("receiveConfirmTestListener")
public class ReceiveConfirmTestListener implements ChannelAwareMessageListener {

  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    try {
      System.out.println("接收到的消息:" + new String(message.getBody()));
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 手工应答
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
  }
}
