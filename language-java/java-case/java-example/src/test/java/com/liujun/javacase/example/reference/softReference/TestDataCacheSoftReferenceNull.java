package com.liujun.javacase.example.reference.softReference;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 测试最基础的软引用
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestDataCacheSoftReferenceNull {

  private static final int MAX_LENGTH = 1000;

  /**
   * 运行时JVM参数 -XX:+PrintGCDetails -Xmx1m -Xms1m
   * @param args
   */
  public static void main(String[] args) {
    DataCacheSoftReference instance = DataCacheSoftReference.getInstance();

    for (int i = 0; i < MAX_LENGTH; i++) {
      String data = RandomStringUtils.randomAlphanumeric(1024);
      instance.put(String.valueOf(i), data);
      data = null;
    }

    // System.gc();

    for (int i = 0; i < MAX_LENGTH; i++) {
      System.out.println(instance.get(String.valueOf(i)));
    }
  }
}
