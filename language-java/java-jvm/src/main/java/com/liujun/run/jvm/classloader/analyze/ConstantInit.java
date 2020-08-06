package com.liujun.run.jvm.classloader.analyze;

/**
 * 静态块的初始化
 *
 * @author liujun
 * @version 0.0.1
 */
public class ConstantInit {

  static {
    value = 3;
    // 编译错误，提示非法的向前引用，正确的是静态变量需要在静态块之前
    // System.out.println(value);
  }

  public static int value = 2;
}
