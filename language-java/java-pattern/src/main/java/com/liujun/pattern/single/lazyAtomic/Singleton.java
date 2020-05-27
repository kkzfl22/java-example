package com.liujun.pattern.single.lazyAtomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 在饿汉式的基础上使用原子变量解决并发问题
 *
 * @author liujun
 * @version 0.0.1
 */
public class Singleton {

  private static AtomicReference<Singleton> instance = new AtomicReference<Singleton>(null);

  private Singleton() {}

  public static Singleton getInstance() {
    Singleton instanceData = instance.get();

    if (instanceData != null) {
      return instanceData;
    }

    // 基于原子对象的比较设置操作
    instance.compareAndSet(null, new Singleton());

    return instance.get();
  }
}
