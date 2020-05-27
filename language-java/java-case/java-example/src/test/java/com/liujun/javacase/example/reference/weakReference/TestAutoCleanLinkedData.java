package com.liujun.javacase.example.reference.weakReference;

/**
 * @author liujun
 * @version 0.0.1
 */
public class TestAutoCleanLinkedData {

  public static void main(String[] args) {
    AutoCleanLinkedData.getInstance().clean();
    System.gc();
    System.out.println("");
    AutoCleanLinkedData.getInstance().print();
  }
}
