package com.liujun.run.jvm.memory.outofmemory.outof;

import com.liujun.run.jvm.memory.outofmemory.RunOutOfInf;

import java.util.ArrayList;
import java.util.List;

/**
 * java堆溢出
 *
 * @author liujun
 * @version 0.0.1
 */
public class HeapOutOf implements RunOutOfInf {

  public static final HeapOutOf INSTANCE = new HeapOutOf();

  @Override
  public void runInvoke() {
    try {
      int maxMemory = 256;
      List<DataInfo> byteData = new ArrayList<>(maxMemory);

      for (int i = 0; i < maxMemory; i++) {
        for (int j = 0; j < 10; j++) {
          byteData.add(new DataInfo(1024 * 1024));
        }
        Thread.sleep(500);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private class DataInfo {
    private final Byte[] data;

    public DataInfo(int size) {
      this.data = new Byte[size];
    }

    public Byte[] getData() {
      return data;
    }
  }
}
