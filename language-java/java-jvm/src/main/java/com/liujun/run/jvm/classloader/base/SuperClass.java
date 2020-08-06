package com.liujun.run.jvm.classloader.base;

/**
 * 初始使用类的字段演示
 *
 * <p>1,通过子类引用父类的静态字段，不会导致子类初始化。
 *
 * @author liujun
 * @version 0.0.1
 */
public class SuperClass {

  static {
    System.out.println("SuperClass init!");
  }

  public static int value = 12;
}
