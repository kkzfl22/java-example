package com.liujun.run.jvm.diagnose;

import java.util.Map;

/**
 * 通过java程序打印虚拟机中的栈信息，非常的简单,可满足jstack中的大部分功能
 *
 * @author liujun
 * @version 0.0.1
 */
public class OutAllStackTraces {

  public static void main(String[] args) {
    for (Map.Entry<Thread, StackTraceElement[]> entryItem : Thread.getAllStackTraces().entrySet()) {

      // 仅打印当前线程
      if (entryItem.getKey().equals(Thread.currentThread())) {
        continue;
      }

      System.out.println(entryItem.getKey());

      for (StackTraceElement element : entryItem.getValue()) {
        System.out.println("\t" + element + ";");
      }
    }
  }
}
