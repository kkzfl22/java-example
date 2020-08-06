package com.liujun.javacase.example.memory.heap;

import com.liujun.javacase.example.memory.Addresser;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 进行堆分配内存测试
 *
 * @author liujun
 * @version 0.0.1
 */
public class JavaHeapRun {

  public static void main(String[] args) throws Exception {
    System.out.println("start allocate ..");

    // Thread.sleep(30000L);
    System.out.println("start heap ..");
    List<ByteBuffer> data = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      //      try {
      //        Thread.sleep(1 * 5 * 1000);
      //      } catch (InterruptedException e) {
      //        e.printStackTrace();
      //      }
      ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024 * 64);
      System.out.println("alloct :" + i + byteBuffer.limit() + ",i:" + i);
      System.out.println("memory address :" + Addresser.addressOf(byteBuffer));
      System.out.println("memory address2:" + System.identityHashCode(byteBuffer));
      data.add(byteBuffer);
    }

    OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    System.out.println("out allocte");
    Thread.sleep(10 * 60 * 1000);
  }
}
