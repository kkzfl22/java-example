package com.liujun.run.jvm.memory.outofmemory.outof;

import com.liujun.run.jvm.memory.outofmemory.RunOutOfInf;

import java.nio.ByteBuffer;

/**
 * 直接内存溢出
 *
 * @author liujun
 * @version 0.0.1
 */
public class DirectMemoryOutOf implements RunOutOfInf {

  public static final DirectMemoryOutOf INSTANCE = new DirectMemoryOutOf();

  @Override
  public void runInvoke() {
    // 申请直接内存128M
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 128);
    System.out.println(buffer.limit());
  }
}
