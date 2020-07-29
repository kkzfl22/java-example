package com.liujun.run.jvm.jvmclass.constant;

import org.junit.Assert;
import org.junit.Test;

/**
 * 测试字节码
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestByteCodeCommand {

  @Test
  public void runGetByteCode() {
    int sumLength = 0;
    for (int i = 0; i < 14; i++) {
      for (int j = 0; j < 16; j++) {

        String start = Integer.toHexString(i);
        start += Integer.toHexString(j);

        int value = Integer.parseInt(start, 16);

        ByteCodeCommand data = ByteCodeCommand.getCommand((byte) value);
        System.out.println("当前指令:0x" + start + ",-->" + data);

        if (null != data) {
          sumLength++;
        }
      }
    }

    System.out.println("总共：" + sumLength + "条指令");
    Assert.assertEquals(202, sumLength);
  }
}
