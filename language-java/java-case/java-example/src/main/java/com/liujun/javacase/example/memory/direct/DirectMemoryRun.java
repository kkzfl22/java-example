package com.liujun.javacase.example.memory.direct;

import java.nio.ByteBuffer;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DirectMemoryRun {

  public static void main(String[] args) throws InterruptedException {

    System.out.println("start allocate direct ..");
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(1 * 10 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 64);
      System.out.println("alloct :" + i + byteBuffer.limit());
    }
    System.out.println("out");
    Thread.sleep(60 * 10 * 1000);
  }
}
