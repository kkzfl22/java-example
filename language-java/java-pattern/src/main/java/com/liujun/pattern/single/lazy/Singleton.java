package com.liujun.pattern.single.lazy;

/**
 * 懒汉式
 *
 * <p>只能单线程环境下使用，如果出现并发请求，会造成多个实例
 *
 * @author liujun
 * @version 0.0.1
 */
public class Singleton {

  private static Singleton singleton;

  private Singleton() {}

  public static Singleton getInstance() {
    if (null == singleton) {
      singleton = new Singleton();
    }

    return singleton;
  }
}
