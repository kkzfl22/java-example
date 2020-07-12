package com.liujun.run.jvm.garbage.collect.hotspot;

import com.liujun.run.jvm.garbage.collect.RunGarbageCollectInf;
import com.liujun.run.jvm.garbage.collect.constant.GarbageCollectCfg;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过对象的年龄来晋升到old generation
 *
 * <p>-verbose:gc -Xmn10m -Xmx20m -Xms20m -XX:+PrintGCDetails -XX:+PrintGCDetails -XX:+UseSerialGC
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 *
 * <p>-verbose:gc -Xmn10m -Xmx20m -Xms20m -XX:+PrintGCDetails -XX:+PrintGCDetails -XX:+UseSerialGC
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 *
 * @author liujun
 * @version 0.0.1
 */
public class GarbageCollectAgeThreshold implements RunGarbageCollectInf {

  public static final GarbageCollectAgeThreshold INSTANCE = new GarbageCollectAgeThreshold();

  @Override
  public void garbageCollect() {

    byte[] allocation1, allocation2, allocation3, allocation4;
    allocation1 = new byte[GarbageCollectCfg._1M / 4];
    // allocation2 = new byte[GarbageCollectCfg._1M / 4];

    allocation3 = new byte[GarbageCollectCfg._1M * 4];
    allocation4 = new byte[GarbageCollectCfg._1M * 4];

    allocation4 = null;
    allocation4 = new byte[GarbageCollectCfg._1M * 4];
  }

  // 塞满Eden区，局部变量会被回收,作为触发GC的小工具
  private static void YGC(int edenSize) {
    for (int i = 0; i < edenSize; i++) {
      byte[] byte1m = new byte[1 * 1024 * 1024];
    }
  }
}
