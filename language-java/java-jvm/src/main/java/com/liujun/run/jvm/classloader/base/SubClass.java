package com.liujun.run.jvm.classloader.base;

/**
 * @author liujun
 * @version 0.0.1
 */
public class SubClass extends SuperClass {

  static {
    System.out.println("SubClass init!");
  }

  public static int outValue = 15;
}
