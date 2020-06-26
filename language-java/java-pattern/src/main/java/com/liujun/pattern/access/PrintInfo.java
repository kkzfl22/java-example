package com.liujun.pattern.access;

public interface PrintInfo extends Ivisitor {
  /** 打印信息 */
  void print();

  /**
   * 进行计算
   *
   * @return
   */
  int count();
}
