package com.liujun.run.jvm.jvmclass.sourceJavaFile;

/**
 * 测试异常信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestException {

  public static final int NUM = 100;

  public int runception(int a) throws Exception {
    if (a < 0) {
      throw new IllegalArgumentException(" data < 0");
    }

    return a + NUM;
  }
}
