package com.liujun.run.jvm.jvmclassEngine.staticDispatch;

/**
 * 静态分派演示
 *
 * @author liujun
 * @version 0.0.1
 */
public class StaticDispatch {

  abstract static class Human {}

  static class Man extends Human {}

  static class Woman extends Human {}

  public void sayHello(Human hum) {
    System.out.println("hello,hum");
  }

  public void sayHello(Man man) {
    System.out.println("hello,man");
  }

  public void sayHello(Woman woman) {
    System.out.println("hello,woman");
  }

  public static void main(String[] args) {
    Human man = new Man();
    Human woman = new Woman();
    StaticDispatch dispatch = new StaticDispatch();
    dispatch.sayHello(man);
    dispatch.sayHello(woman);
    // 做强制转换后的引用
    System.out.println("做强转后的类型加载");
    dispatch.sayHello((Man) man);
    dispatch.sayHello((Woman) woman);
  }
}
