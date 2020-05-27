package com.liujun.pattern.single.lazyDoubleCheck;

/**
 * 饿汉式加入双重检查
 *
 * <p>双重检查机制确保了对象只会加载一个
 *
 * @author liujun
 * @version 0.0.1
 */
public class Singleton {

  /** 加入volatile保证对象的内存可见性，让所有的对象即时可见 */
  private static volatile Singleton instance;

  private Singleton() {}

  public static Singleton getInstance() {
    if (null == instance) {
      synchronized (Singleton.class) {
        // 双重检查机制，确保了对象只会创建一份
        if (null == instance) {
          instance = new Singleton();
        }
      }
    }

    return instance;
  }
}
