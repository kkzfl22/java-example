package com.liujun.run.jvm.garbage.collect.hotspot;

import com.liujun.run.jvm.garbage.collect.RunGarbageCollectInf;
import com.liujun.run.jvm.garbage.collect.constant.GarbageCollectCfg;

/**
 * 大对象分配， 针对超过指定大小的对象，可直接进入老年代
 *
 * <p>配制当对象大小超过1M时，则直接进入老年代
 *
 * <p>-Xmn10m -Xmx20m -Xms20m -XX:+PrintGCDetails -verbose:GC -XX:+UseSerialGC
 * -XX:PretenureSizeThreshold=2097154
 *
 * @author liujun
 * @version 0.0.1
 */
public class GarbageCollectBigAllocation implements RunGarbageCollectInf {

  public static final GarbageCollectBigAllocation INSTANCE = new GarbageCollectBigAllocation();

  @Override
  public void garbageCollect() {

    byte[] allocation1;
    // 1,配制了2M的对象进入old generation,当配制2M大小的内存时，则对象直接进入了old generation
    allocation1 = new byte[GarbageCollectCfg._1M * 2];
  }
}
