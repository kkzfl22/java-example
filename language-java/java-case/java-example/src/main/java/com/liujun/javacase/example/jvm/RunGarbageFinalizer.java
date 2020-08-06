package com.liujun.javacase.example.jvm;

/**
 * @author liujun
 * @version 0.0.1
 */
public class RunGarbageFinalizer {

  private static RunGarbageFinalizer INSTANCE = null;

  public void isAlive() {
    System.out.println("curr object is alive");
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("curr invoke finalize");
    // 重新对对象赋值
    INSTANCE = this;
  }

  public static void main(String[] args) throws InterruptedException {

    INSTANCE = new RunGarbageFinalizer();
    // 执行第一次finalize方法
    INSTANCE = null;
    System.out.println("start gc..");
    System.gc();
    Thread.sleep(500);

    // 执行对象对比
    if (INSTANCE != null) {
      INSTANCE.isAlive();
    } else {
      System.out.println("curr1  object is did");
    }

    // 执行第二次的gc
    INSTANCE = null;
    System.gc();
    Thread.sleep(500);

    if (INSTANCE != null) {
      INSTANCE.isAlive();
    } else {
      System.out.println("curr2 object is did ");
    }
  }
}
