package com.liujun.run.jvm.jvmclass;

import org.junit.Test;

/**
 * 获取标识
 *
 * @author liujun
 * @version 0.0.1
 */
public class RunGetFlag {

  @Test
  public void getFlag() {
    short value = (short) 0x0021;
    short flag = (short) 0x0001;
    short notValue = (short) ~flag;

    System.out.println((value & flag) == flag);
  }
}
