package com.liujun.depend.client;

import com.liujun.depend.service.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

/**
 * @author liujun
 * @version 0.0.1
 */
@Configuration
@Order(10)
public class ClientConfig {

  @Autowired private ClientStart clientInstance;

  @PostConstruct
  public void register() {
    StartService.register(clientInstance);
  }
}
