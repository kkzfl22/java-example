package com.liujun.run.jvm.jvmclass.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性的访问修饰符枚举
 *
 * @author liujun
 * @version 0.0.1
 */
public enum FieldAccessFlagEnum {

  /** 公共的访问修饰符 */
  ACC_PUBLIC((short) 0x0001),

  /** 私有的修饰符 */
  ACC_PRIVATE((short) 0x0002),

  /** protected访问修饰符 */
  ACC_PROTECTED((short) 0x0004),

  /** 静态的访问修饰符 */
  ACC_STATIC((short) 0x0008),

  /** final的修饰符 */
  ACC_FINAL((short) 0x0010),

  /** volatile修饰符 */
  ACC_VOLATILE((short) 0x0040),

  /** 编译器自动产生标识 */
  ACC_TRANSIENT((short) 0x1000),

  /** 是否为enum */
  ACC_ENUM((short) 0x4000),
  ;

  private short flag;

  FieldAccessFlagEnum(short flag) {
    this.flag = flag;
  }

  public short getFlag() {
    return flag;
  }

  /**
   * 获取标识符
   *
   * @param accessflags
   * @return
   */
  public static FieldAccessFlagEnum[] accessFlags(short accessflags) {
    List<FieldAccessFlagEnum> flagList = new ArrayList<>();
    for (FieldAccessFlagEnum flag : values()) {
      if ((flag.getFlag() & accessflags) == flag.getFlag()) {
        flagList.add(flag);
      }
    }

    // 进行转换
    FieldAccessFlagEnum[] out = new FieldAccessFlagEnum[flagList.size()];
    for (int i = 0; i < flagList.size(); i++) {
      out[i] = flagList.get(i);
    }
    return out;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FieldAccessFlagEnum{");
    sb.append("name=").append(this.name()).append(",");
    sb.append("flag=").append(flag);
    sb.append('}');
    return sb.toString();
  }
}
