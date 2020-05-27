package com.liujun.depend.service;

import java.util.ArrayList;
import java.util.List;

/**
 * 启支服务
 *
 * @author liujun
 * @version 0.0.1
 */
public class StartService {

  private static final List<StartInterface> DATA_LIST = new ArrayList<StartInterface>();

  public static void register(StartInterface start) {
    DATA_LIST.add(start);
  }

  public static void run() {
    for (StartInterface flowItem : DATA_LIST) {
      System.out.println(flowItem.start());
    }
  }
}
