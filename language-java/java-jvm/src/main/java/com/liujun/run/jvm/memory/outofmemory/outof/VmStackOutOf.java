package com.liujun.run.jvm.memory.outofmemory.outof;

import com.liujun.run.jvm.memory.outofmemory.RunOutOfInf;

/**
 * @author liujun
 * @version 0.0.1
 */
public class VmStackOutOf implements RunOutOfInf {

  public static final VmStackOutOf INSTANCE = new VmStackOutOf();

  @Override
  public void runInvoke() {
    while (true) {
      new Thread(
              () -> {
                donStop();
              })
          .start();
    }
  }

  private void donStop() {
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
