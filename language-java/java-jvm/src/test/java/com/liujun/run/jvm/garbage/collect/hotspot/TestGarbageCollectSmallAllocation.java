package com.liujun.run.jvm.garbage.collect.hotspot;

/**
 * 测试新生代对象回收
 *
 * <p>需指定JVM的参数
 *
 * <p>-Xmn10m -Xmx20m -XX:+PrintGCDetails -verbose:gc
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestGarbageCollectSmallAllocation {

  public static void main(String[] args) {
    GarbageCollectSmallAllocation.INSTANCE.garbageCollect();
  }
}
