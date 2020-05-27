package com.liujun.pattern.single.lazySynchronized;

/**
 * 饿汉式的单例加同步
 *
 * <p>此方法解决了饿汉式在不能在并发环境下加载的问题，但同时又出现了另外的问题
 *
 * <p>性能很差，每个进入的请求都会被加载排队
 *
 * @author liujun
 * @version 0.0.1
 */
public class Singleton {

  private static Singleton instance;

  private Singleton() {}

  public static synchronized Singleton getInstance() {
    if (null == instance) {
      instance = new Singleton();
    }
    return instance;
  }
}
