package com.liujun.run.jvm.jvmclass.sourceJavaFile;

/**
 * @author liujun
 * @version 0.0.1
 */
public class DataCountImpl implements DataInf {

  private static final int STATIC_FIELD = 12;

  private int field = 10;

  @Override
  public int count(int input) {
    return STATIC_FIELD + field + input + 20;
  }
}