package com.liujun.run.jvm.garbage.collect.hotspot;

/**
 * 测试serial垃圾收集
 *
 * @author liujun
 * @version 0.0.1
 */
public class RunGarbageCollectSerial {

  public static void main(String[] args) {
    GarbageCollectSerial.INSTANCE.garbageCollect();
    System.out.println("run finish");
  }
}
