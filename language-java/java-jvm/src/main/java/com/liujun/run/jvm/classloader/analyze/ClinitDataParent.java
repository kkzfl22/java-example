package com.liujun.run.jvm.classloader.analyze;

/**
 * 类的引用加载顺序
 *
 * @author liujun
 * @version 0.0.1
 */
public class ClinitDataParent {

  static class Parsen {
    public static int value = 2;

    static {
      value = 8;
    }
  }

  static class Sub extends Parsen {
    public static int subValue = value;
  }

  public static void main(String[] args) {
    // 打印当前的值是8，而非2，这是由于父类的clinit方先于子类执行
    System.out.println(Sub.subValue);
  }
}
