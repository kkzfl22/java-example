package com.liujun.run.jvm.jvmclass.bean.constanttype;

import lombok.Data;

/**
 * 按照高位在前存储的int类型的值
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class IntegerEntity extends ConstantBase {

  /** 按高位在前存储的int值 */
  private int bytes;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("IntegerEntity{");
    sb.append("bytes=").append(bytes);
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
