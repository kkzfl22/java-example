package com.liujun.run.jvm.garbage.collect.hotspot;

/**
 * 测试新生代对象回收
 *
 * <p>指定参数当超过指定的数值时，则直接进入老年代
 *
 * <p>需指定JVM的参数 -XX:PretenureSizeThreshold=2097154 以b为单位
 *
 * <p>-Xmn10m -Xmx20m -Xms20m -XX:+PrintGCDetails -verbose:GC -XX:+UseSerialGC *
 * -XX:PretenureSizeThreshold=2097154
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestGarbageCollectBigAllocation {

  public static void main(String[] args) {
    GarbageCollectBigAllocation.INSTANCE.garbageCollect();
  }
}
