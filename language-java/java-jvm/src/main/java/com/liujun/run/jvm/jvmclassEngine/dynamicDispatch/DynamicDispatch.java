package com.liujun.run.jvm.jvmclassEngine.dynamicDispatch;

/**
 * 方法动态分派演示
 *
 * @author liujun
 * @version 0.0.1
 */
public class DynamicDispatch {

  abstract static class Human {
    protected abstract void sayHello();
  }

  static class Man extends Human {
    @Override
    protected void sayHello() {
      System.out.println("hello,man");
    }
  }

  static class Woman extends Human {
    @Override
    protected void sayHello() {
      System.out.println("hello,woman");
    }
  }

  public static void main(String[] args) {
    Human man = new Man();
    Human woman = new Woman();

    man.sayHello();
    woman.sayHello();

    man = new Woman();
    man.sayHello();
  }
}
