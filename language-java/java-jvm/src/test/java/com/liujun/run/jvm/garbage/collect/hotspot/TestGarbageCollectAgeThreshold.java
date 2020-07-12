package com.liujun.run.jvm.garbage.collect.hotspot;

/**
 * 测试设计对象晋升年龄的条件
 *
 * <p>MaxTenuringThreshold = 1
 *
 * <p>MaxTenuringThreshold = 15
 *
 * <p>-Xmn10m -Xmx20m -verbose:gc -XX:+PrintGCDetails -XX:MaxTenuringThreshold=1
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestGarbageCollectAgeThreshold {

  public static void main(String[] args) {
    GarbageCollectAgeThreshold.INSTANCE.garbageCollect();
  }
}
