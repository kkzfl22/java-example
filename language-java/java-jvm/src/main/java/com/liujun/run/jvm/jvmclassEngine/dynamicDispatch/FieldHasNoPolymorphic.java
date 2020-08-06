package com.liujun.run.jvm.jvmclassEngine.dynamicDispatch;

/**
 * 字段不参与多态
 *
 * @author liujun
 * @version 0.0.1
 */
public class FieldHasNoPolymorphic {

  static class Father {
    public int money;

    public Father() {
      this.money = 2;
      showMeTheMoney();
    }

    public void showMeTheMoney() {
      System.out.println("I am father, i have $" + money);
    }
  }

  static class Son extends Father {
    public int money = 3;

    public Son() {
      this.money = 4;
      showMeTheMoney();
    }

    public void showMeTheMoney() {
      System.out.println("I am son,I have $" + money);
    }
  }

  public static void main(String[] args) {
    Father son = new Son();
    System.out.println("curr money:" + son.money);
  }
}
