package com.liujun.pattern.single.hungry;

/**
 * 采用饿汉式的写法
 *
 * <p>写法直接，简单，无并发问题，但会在系统启动时就加载，如果启动资源较重，会导致启动时间过长
 *
 * @author liujun
 * @version 0.0.1
 */
public class Singleton {

  private static final Singleton INSTANCE = new Singleton();

  private Singleton() {}

  public static Singleton getInstance() {
    return INSTANCE;
  }
}
