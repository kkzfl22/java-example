package com.liujun.depend.client;

import com.liujun.depend.service.StartInterface;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @version 0.0.1
 */
@Service
public class ClientStart implements StartInterface {

  public String start() {
    System.out.println("client1 start ");
    return "client1 invoke";
  }
}
