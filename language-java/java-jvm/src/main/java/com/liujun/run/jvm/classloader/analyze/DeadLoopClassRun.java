package com.liujun.run.jvm.classloader.analyze;

/**
 * 进行多线程同时初始化的验证
 *
 * @author liujun
 * @version 0.0.1
 */
public class DeadLoopClassRun {

  public static class DeadLoop {
    /** 对于静态加载块，jvm保证了仅加载一次 */
    static {
      System.out.println("start dead loop ....");
      // 不加if，编译器将拒绝编译
      if (true) {
        int index = 0;
        while (index < 5) {
          try {
            // 将当前的线程休眠
            Thread.sleep(2000);
            System.out.println("curr index :" + index);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          index++;
        }
      }
    }
  }

  public static void main(String[] args) {
    Runnable run =
        new Runnable() {
          @Override
          public void run() {
            System.out.println("start thread ..... :" + Thread.currentThread().getId());
            new DeadLoop();
            System.out.println("finish thread ..... :" + Thread.currentThread().getId());
          }
        };

    Thread thread1 = new Thread(run);
    Thread thread2 = new Thread(run);

    thread1.start();
    thread2.start();
  }
}
