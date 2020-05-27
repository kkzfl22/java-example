package com.liujun.depend.client;

import com.liujun.depend.service.StartInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 * @version 0.0.1
 */
@Service
public class Client2Start implements StartInterface {

  public String start() {
    System.out.println("client2 start ");
    return "client2 invoke";
  }
}
