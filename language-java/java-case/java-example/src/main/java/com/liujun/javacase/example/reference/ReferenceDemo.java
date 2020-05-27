package com.liujun.javacase.example.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/** 进行软引用与弱引用的使用 */
public class ReferenceDemo {

  public static void main(String[] args) {
    // 定义一个强引用
    String must = new String("abc");
    // 定义软引用
    SoftReference<String> softReference = new SoftReference<String>(must);
    // 去掉强引用
    must = null;
    // 执行垃圾收集，当前内存充足，此对象不会被回收
    System.gc();
    // 打印软引用中的对象
    System.out.println("软引用中的在垃圾回收后的对象:" + softReference.get());

    // 弱引用
    String tempValue = new String("123");
    WeakReference<String> weakReference = new WeakReference<String>(tempValue);
    // 去掉强引用
    tempValue = null;
    // 执行垃圾回收
    System.gc();
    // 打印弱引用中的对象
    System.out.println("弱引用中在垃圾回收后的对象:" + weakReference.get());
  }
}
