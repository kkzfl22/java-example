package com.liujun.run.jvm.jvmclassEngine.staticDispatch;

import java.io.Serializable;

/**
 * 重载方法匹配优先级
 *
 * @author liujun
 * @version 0.0.1
 */
public class Overload {

  public static void sayHello(Object ars) {
    System.out.println("hello,object");
  }

  public static void sayHello(int arg) {
    System.out.println("hello,int");
  }

  public static void sayHello(long arg) {
    System.out.println("hello,long");
  }

  public static void sayHello(Character arg) {
    System.out.println("hello,Character");
  }

  public static void sayHello(char arg) {
    System.out.println("hello,char");
  }

  public static void sayHello(char... arg) {
    System.out.println("hello,char...");
  }

  public static void sayHello(Serializable arg) {
    System.out.println("hello,Serializable");
  }

  public static void main(String[] args) {
    // 当存在char类型时，则优先匹配char
    // 当char类型不存在，则优先匹配int类型
    // 当int类型不匹配时，则匹配long类型
    // 然后再匹配char的包装类
    // 再匹配序列化类型
    // 再匹配object类型
    // 最后匹配动态数组类型
    sayHello('a');
  }
}
