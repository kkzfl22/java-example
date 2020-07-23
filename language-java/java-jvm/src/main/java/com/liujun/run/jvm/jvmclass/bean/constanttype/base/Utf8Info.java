package com.liujun.run.jvm.jvmclass.bean.constanttype.base;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import lombok.Data;

import java.util.Arrays;

/**
 * 以UTF8的缩略码的形式存储的常量数据
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class Utf8Info extends ConstantBase {

  /** 一个u2类型的长度 */
  private short length;

  /** 常量的数据信息 */
  private byte[] bytes;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Utf8Info{");
    sb.append("length=").append(length);
    sb.append(", bytes=").append(Arrays.toString(bytes)).append("-->").append(new String(bytes));
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
