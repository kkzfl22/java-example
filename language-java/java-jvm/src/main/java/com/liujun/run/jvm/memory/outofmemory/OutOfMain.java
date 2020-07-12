package com.liujun.run.jvm.memory.outofmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author liujun
 * @version 0.0.1
 */
public class OutOfMain {

  private static final Map<String, RunOutOfInf> OUT_INSTANCE = new HashMap<>();

  static {
    for (OutInstanceEnum instance : OutInstanceEnum.values()) {
      OUT_INSTANCE.put(instance.getKey(), instance.getRunInstance());
    }
  }

  public static void main(String[] args) {
    System.out.println("please input option:");
    outTitle();

    while (true) {
      String option = getOption();
      RunOutOfInf runOut = OUT_INSTANCE.get(option);
      if (null == runOut) {
        System.out.println(" cull is null exists ");
        break;
      }

      runOut.runInvoke();
    }
  }

  public static void outTitle() {
    for (OutInstanceEnum instance : OutInstanceEnum.values()) {
      System.out.println(instance.getMenu());
    }
    System.out.println("-1,system exists!");
  }

  public static String getOption() {
    Scanner sc = new Scanner(System.in);

    return sc.nextLine();
  }
}
