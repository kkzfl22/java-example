package com.liujun.run.jvm.garbage.collect.hotspot;

import com.liujun.run.jvm.garbage.collect.RunGarbageCollectInf;
import com.liujun.run.jvm.garbage.collect.constant.GarbageCollectCfg;

/**
 * 使用serial作为垃圾收集器
 *
 * <p>-Xmn10M -Xmx20M -Xms20M -XX:+UseSerialGC -XX:+PrintGCDetails
 *
 * <p>-Xmn 新生代10M *
 *
 * <p>-Xmx 最大堆
 *
 * <p>-Xmx 最小堆
 *
 * <p>-XX:+UseSerialGC -XX:+PrintGCDetails
 *
 * <p>-XX:MaxTenuringThreshold=1
 *
 * @author liujun
 * @version 0.0.1
 */
public class GarbageCollectSerial implements RunGarbageCollectInf {

  public static final GarbageCollectSerial INSTANCE = new GarbageCollectSerial();

  @Override
  public void garbageCollect() {

    // 分配6M的内存空间
    byte[] newData = new byte[GarbageCollectCfg._1M];
    byte[] newData2 = new byte[GarbageCollectCfg._1M];
    byte[] newData3 = new byte[GarbageCollectCfg._1M];
    byte[] newData4 = new byte[GarbageCollectCfg._1M];
    byte[] newData5 = new byte[GarbageCollectCfg._1M];
    byte[] newData6 = new byte[GarbageCollectCfg._1M];
    // 再分配一个5M的空间，则发生minorGC
    byte[] newData7 = new byte[GarbageCollectCfg._1M * 5];
    System.out.println("gc minor finish");
    //当空间不够就会触发full  GC
    byte[] newData8 = new byte[GarbageCollectCfg._1M * 4];
    System.out.println("full gc finish");
  }
}
