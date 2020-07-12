package com.liujun.run.jvm.garbage.collect.hotspot;

import com.liujun.run.jvm.garbage.collect.RunGarbageCollectInf;
import com.liujun.run.jvm.garbage.collect.constant.GarbageCollectCfg;

/**
 * 小对象分配
 *
 * <p>-Xmn10m -Xmx20m -Xms20m -XX:+PrintGCDetails -verbose:GC -XX:+UseSerialGC
 *
 * @author liujun
 * @version 0.0.1
 */
public class GarbageCollectSmallAllocation implements RunGarbageCollectInf {

  public static final GarbageCollectSmallAllocation INSTANCE = new GarbageCollectSmallAllocation();

  @Override
  public void garbageCollect() {
    try {
      byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
      // 1,Eden分配8M的内存，会将整个Eden区的区域填满
      allocation1 = new byte[GarbageCollectCfg._1M * 2];
      // 新生代内存已经占用达88%
      allocation2 = new byte[GarbageCollectCfg._1M * 2];
      allocation3 = new byte[GarbageCollectCfg._256K * 2];
      allocation4 = new byte[GarbageCollectCfg._256K * 2];
      // eden区的一部分对象将进入survivor区，还有一部分将进行old generation
      allocation5 = new byte[GarbageCollectCfg._128K * 2];

      // 这里将发生一次minorGC
      // allocation1 = new byte[GarbageCollectCfg._128K];
      Thread.sleep(1000);
      System.out.println("结束");

      // 当前allocation1会进行survivor区,新生代对象为空
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
