package com.liujun.run.jvm.tools.jhsdb;

/**
 * 使用jvm参数
 *
 * <p>-Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 *
 * @author liujun
 * @version 0.0.1
 */
public class JHSDB_TestCase {
  static class Test {
    static ObjectHolder staticObj = new ObjectHolder(1, "staticObject");
    ObjectHolder instanceObj = new ObjectHolder(2, "defaultObject");

    void foo() {
      ObjectHolder localObj = new ObjectHolder(3, "fooObject");
      System.out.println("done");

      System.out.println("static object :" + staticObj);
      System.out.println("default object :" + staticObj);
      System.out.println("foo object :" + localObj);
    }
  }

  private static class ObjectHolder {
    private int value;
    private String name;

    public ObjectHolder(int value, String name) {
      this.value = value;
      this.name = name;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("ObjectHolder{");
      sb.append("value=").append(value);
      sb.append(", name='").append(name).append('\'');
      sb.append('}');
      return sb.toString();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    Test instance = new JHSDB_TestCase.Test();
    instance.foo();

    Thread.sleep(1200000);
  }
}
