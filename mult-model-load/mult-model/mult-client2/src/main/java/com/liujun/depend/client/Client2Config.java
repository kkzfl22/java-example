package com.liujun.depend.client;

import com.liujun.depend.service.StartService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author liujun
 * @version 0.0.1
 */
@Configuration
public class Client2Config implements InitializingBean {

  @Autowired private Client2Start clientInstance;

  public void afterPropertiesSet() throws Exception {
    StartService.register(clientInstance);
  }
}
