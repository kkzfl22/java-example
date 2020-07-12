package com.liujun.run.jvm.tools.tracing;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 对象跟踪的示例主函数
 *
 * @author liujun
 * @version 0.0.1
 */
public class TraceRunMain {

  private static final Map<Integer, TraceRunInf> OUT_INSTANCE = new HashMap<>();

  static {
    for (TraceEnum instance : TraceEnum.values()) {
      OUT_INSTANCE.put(instance.getKey(), instance.getRunInstance());
    }
  }

  public static void main(String[] args) {
    System.out.println("please input option:");

    while (true) {
      outTitle();
      int option = getOption();
      TraceRunInf runOut = OUT_INSTANCE.get(option);
      if (null == runOut) {
        System.out.println(" cull is null exists ");
        break;
      }

      Object rsp = runOut.runTrace(option);
      System.out.println("output:" + rsp);
    }
  }

  public static void outTitle() {
    for (TraceEnum instance : TraceEnum.values()) {
      System.out.println(instance.getTitle());
    }
    System.out.println("-1,system exists!");
  }

  public static Integer getOption() {
    Scanner sc = new Scanner(System.in);

    return sc.nextInt();
  }
}
