package com.liujun.run.jvm.jvmclass.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区集合
 *
 * @author liujun
 * @version 0.0.1
 */
public enum FunctionAccessFlagEnum {
  ACC_PUBLIC((short) 0x0001),

  ACC_PRIVATE((short) 0x0002),

  ACC_PROTECTED((short) 0x0004),

  ACC_STATIC((short) 0x0008),

  ACC_FINAL((short) 0x0010),

  ACC_SYNCHRONIZED((short) 0x0020),

  /** 方法是否由编译器产生的桥接方法 */
  ACC_BRIDGE((short) 0x0040),

  /** 是否接收不定参数 */
  ACC_VARARGS((short) 0x080),

  /** 是否为native方法 */
  ACC_NATIVE((short) 0x0100),

  /** 是否为抽象方法 */
  ACC_ABSTRACT((short) 0x0400),
  /** 是否为strictfp */
  ACC_STRICT((short) 0x0800),

  /** 是否由编译器自动产生 */
  ACC_SYNTHETIC((short) 0x1000),
  ;

  /** 方法区访问标志 */
  private short accessFlag;

  FunctionAccessFlagEnum(short accessFlag) {
    this.accessFlag = accessFlag;
  }

  public short getAccessFlag() {
    return accessFlag;
  }

  /**
   * 获取标识符
   *
   * @param accessflags
   * @return
   */
  public static FunctionAccessFlagEnum[] accessFlags(short accessflags) {
    List<FunctionAccessFlagEnum> flagList = new ArrayList<>();
    for (FunctionAccessFlagEnum flag : values()) {
      if ((flag.accessFlag & accessflags) == flag.accessFlag) {
        flagList.add(flag);
      }
    }

    // 进行转换
    FunctionAccessFlagEnum[] out = new FunctionAccessFlagEnum[flagList.size()];
    for (int i = 0; i < flagList.size(); i++) {
      out[i] = flagList.get(i);
    }
    return out;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FunctionAccessFlagEnum{");
    sb.append("name=").append(this.name()).append(",");
    sb.append("accessFlag=").append(accessFlag);
    sb.append('}');
    return sb.toString();
  }
}
