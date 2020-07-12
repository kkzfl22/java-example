package com.liujun.run.jvm.memory.outofmemory.outof;

import com.liujun.run.jvm.memory.outofmemory.RunOutOfInf;

/**
 * java本地方法栈溢出
 *
 * @author liujun
 * @version 0.0.1
 */
public class StackOver
        implements RunOutOfInf {

  public static final StackOver INSTANCE = new StackOver();

  @Override
  public void runInvoke() {
    this.runInvoke();
  }
}
