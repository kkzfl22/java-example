package com.liujun.pattern.single.hungry;

/**
 * 采用饿汉式的变种写法
 *
 * <p>
 *
 * @author liujun
 * @version 0.0.1
 */
public class SingletonStatic {

  private static final SingletonStatic INSTANCE;

  static {
    INSTANCE = new SingletonStatic();
  }

  private SingletonStatic() {}

  public static SingletonStatic getInstance() {
    return INSTANCE;
  }
}
