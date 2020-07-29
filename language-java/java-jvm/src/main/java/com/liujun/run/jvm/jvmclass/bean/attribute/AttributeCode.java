package com.liujun.run.jvm.jvmclass.bean.attribute;

import com.liujun.run.jvm.jvmclass.bean.exception.ExceptionTable;
import lombok.Data;

import java.util.Arrays;

/**
 * 当前为code的属性信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeCode extends AttributeBase {

  /** 操作数栈深度最大值 */
  private short maxStack;

  /** 局部变量表所需的存储空间 */
  private short maxLocals;

  /** 字节码所占空间的大小 */
  private int codeLength;

  /** 用于存储字节码指令的一系列字节流,即为字节码指令 */
  private byte[] code;

  /** 异常表长度,非必须 */
  private short exceptionTableLength;

  /** 异常表信息 */
  private ExceptionTable[] exceptionInfo;

  /** 属性的长度 */
  private short attributeCount;

  /** 属性的信息 */
  private AttributeBase[] attribute;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeCode{");
    sb.append("maxStack=").append(maxStack);
    sb.append(", maxLocals=").append(maxLocals);
    sb.append(", codeLength=").append(codeLength);
    sb.append(", code=").append(code);
    sb.append(", exceptionTableLength=").append(exceptionTableLength);
    sb.append(", exceptionInfo=").append(Arrays.toString(exceptionInfo));
    sb.append(", attributeCount=").append(attributeCount);
    sb.append(", attribute=").append(Arrays.toString(attribute));
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
