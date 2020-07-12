package com.liujun.run.jvm.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ProblemRunMain {

  private static final Map<Integer, ProblemRunInf> OUT_INSTANCE = new HashMap<>();

  static {
    for (ProblemRunEnum instance : ProblemRunEnum.values()) {
      OUT_INSTANCE.put(instance.getKey(), instance.getRunInstance());
    }
  }

  public static void main(String[] args) {
    System.out.println("please input option:");
    outTitle();

    while (true) {
      int option = getOption();
      ProblemRunInf runOut = OUT_INSTANCE.get(option);
      if (null == runOut) {
        System.out.println(" cull is null exists ");
        break;
      }

      runOut.runInvoke();
    }
  }

  public static void outTitle() {
    for (ProblemRunEnum instance : ProblemRunEnum.values()) {
      System.out.println(instance.getTitle());
    }
    System.out.println("-1,system exists!");
  }

  public static Integer getOption() {
    Scanner sc = new Scanner(System.in);

    return sc.nextInt();
  }
}
