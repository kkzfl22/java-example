package com.liujun.javacase.example.reference.softReference;

/**
 * 测试最基础的软引用
 *
 * @author liujun
 * @version 0.0.1
 */
public class TestDataCacheSoftReference {

  public static void main(String[] args) {
    DataCacheSoftReference instance = DataCacheSoftReference.getInstance();
    String data = "123456";
    instance.put("111", data);
    data = null;
    System.gc();
    System.out.println("强引用对象:" + data);
    System.out.println(instance.get("111"));
  }
}
