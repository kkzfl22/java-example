package com.liujun.middleware.rabbitmq.spring.manual.publish;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试
 *
 * @author liujun
 * @version 0.0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:manual/topic/ApplicationtContext.xml")
public class TestConfirm {

  @Autowired private PublishService publishService;

  private static final String EXCHANGE_NAME = "myexchangeDirect";

  private static final String QUEU_NAME = "myExchangeQueue";

  /**
   * exchange正确，queue正确 confirm会执行，ack为true
   *
   * @throws Exception
   */
  @Test
  public void testACKOk() throws Exception {
    String message = "currtime :" + System.currentTimeMillis();
    System.out.println("send msg:" + message);
    publishService.send(EXCHANGE_NAME, QUEU_NAME, message);
    Thread.sleep(1000);
  }

  /**
   * exchange错误,queue正确 confirm会执行，ack为false
   *
   * @throws Exception
   */
  @Test
  public void testAckFail() throws Exception {
    String message = "currtime :" + System.currentTimeMillis();
    System.out.println("send msg:" + message);
    publishService.send(EXCHANGE_NAME + "11", QUEU_NAME, message);
    Thread.sleep(1000);
  }

  /**
   * EXCHANGE正确，queue错误，confirm会执行，ack=true，失败会执行
   *
   * @throws Exception
   */
  @Test
  public void testQueuFail() throws Exception {
    String message = "currtime :" + System.currentTimeMillis();
    System.out.println("send msg:" + message);
    publishService.send(EXCHANGE_NAME, QUEU_NAME + "11", message);
    Thread.sleep(1000);
  }

  /**
   * EXCHANGE错误，queue错误，confirm会执行，ack=false
   *
   * @throws Exception
   */
  @Test
  public void testFailALL() throws Exception {
    String message = "currtime :" + System.currentTimeMillis();
    System.out.println("send msg:" + message);
    publishService.send(EXCHANGE_NAME + "11", QUEU_NAME + "11", message);
    Thread.sleep(1000);
  }
}
