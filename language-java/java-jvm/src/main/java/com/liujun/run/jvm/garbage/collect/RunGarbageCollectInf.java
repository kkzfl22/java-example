package com.liujun.run.jvm.garbage.collect;

/**
 * 运行垃圾收集器的相关接口
 *
 * @author liujun
 * @version 0.0.1
 */
public interface RunGarbageCollectInf {

  /** 执行垃圾收集器 */
  void garbageCollect() throws InterruptedException;
}
