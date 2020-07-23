package com.liujun.run.jvm.jvmclass.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问修饰符的枚举
 *
 * @author liujun
 * @version 0.0.1
 */
public enum AccessFlagsEnum {
  /** 当前标识为公共的访问修饰符 */
  ACC_PUBLIC((short) 0x0001),

  /** 是否被声明为final，只有类可以设置 */
  ACC_FINAL((short) 0x0010),

  /** 是否被允许使用invokespecial字节码指令的新主义 */
  ACC_SUPER((short) 0x0020),

  /** 标识这是一个接口 */
  ACC_INTERFACE((short) 0x0200),

  /** 标识是否为 abstract类型 */
  ACC_ABSTRACT((short) 0x0400),

  /** 标识这个类并非由用户代码产生 */
  ACC_SYNTHETIC((short) 0x1000),

  /** 标识这是一个注解 */
  ACC_ANNOTATION((short) 0x2000),

  /** 标识这是一个枚举 */
  ACC_ENUM((short) 0x4000),

  /** 标识这是一个模块 */
  ACC_MODULE((short) 0x8000),
  ;

  private short flag;

  AccessFlagsEnum(short flag) {
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
  public static AccessFlagsEnum[] accessFlags(short accessflags) {
    List<AccessFlagsEnum> flagList = new ArrayList<>();
    for (AccessFlagsEnum flag : values()) {
      if ((flag.getFlag() & accessflags) == flag.getFlag()) {
        flagList.add(flag);
      }
    }

    // 进行转换
    AccessFlagsEnum[] out = new AccessFlagsEnum[flagList.size()];
    for (int i = 0; i < flagList.size(); i++) {
      out[i] = flagList.get(i);
    }
    return out;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AccessFlagsEnum{");
    sb.append("name=").append(this.name()).append(",");
    sb.append("flag=").append(flag).append(",");
    sb.append('}');
    return sb.toString();
  }
}
