package com.liujun.pattern.single.staticprivate;

/**
 * 静态内部类的方式
 *
 * @author liujun
 * @version 0.0.1
 */
public class Singleton {

  private Singleton() {}

  private static class SingletonInstance {
    private static final Singleton INSTANCE = new Singleton();
  }

  public static Singleton getInstance() {
    return SingletonInstance.INSTANCE;
  }
}
