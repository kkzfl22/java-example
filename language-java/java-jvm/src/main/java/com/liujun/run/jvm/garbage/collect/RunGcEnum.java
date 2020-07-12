package com.liujun.run.jvm.garbage.collect;

import com.liujun.run.jvm.garbage.collect.hotspot.GarbageCollectSerial;

/**
 * 运行的GC选项信息
 *
 * @author liujun
 * @version 0.0.1
 */
public enum RunGcEnum {
  /** 单线程暂停垃圾收集器 */
  SERIAL_SERIALOLD(
      1,
      GarbageCollectSerial.INSTANCE,
      "1,run serial/serial old垃圾收集器组合",
      "-XX:+UseSerialGC -XX:+PrintGCDetails");

  /** 当前的标识符 */
  private Integer key;

  /** 实例对象 */
  private RunGarbageCollectInf instance;

  /** 相关的描述 */
  private String describe;

  /** JVM相关的参数 */
  private String useGcParam;

  RunGcEnum(Integer key, RunGarbageCollectInf instance, String describe, String useGcParam) {
    this.key = key;
    this.instance = instance;
    this.describe = describe;
    this.useGcParam = useGcParam;
  }

  public Integer getKey() {
    return key;
  }

  public RunGarbageCollectInf getInstance() {
    return instance;
  }

  public String getDescribe() {
    return describe;
  }

  public String getUseGcParam() {
    return useGcParam;
  }
}
