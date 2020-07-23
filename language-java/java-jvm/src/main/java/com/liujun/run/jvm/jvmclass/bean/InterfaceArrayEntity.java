package com.liujun.run.jvm.jvmclass.bean;

import lombok.Data;

import java.util.Arrays;

/**
 * 实现的接口集合
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class InterfaceArrayEntity {

  /** 长度 */
  private short length;

  /** 指向常量池结构的索引 */
  private short[] interfaceIndex;

  public static InterfaceArrayEntity newInstance(short length) {
    InterfaceArrayEntity instance = new InterfaceArrayEntity();
    instance.length = length;
    instance.interfaceIndex = new short[length];
    return instance;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("InterfaceArrayEntity{");
    sb.append("length=").append(length);
    sb.append(", interfaceIndex=").append(Arrays.toString(interfaceIndex));
    sb.append('}');
    return sb.toString();
  }
}
