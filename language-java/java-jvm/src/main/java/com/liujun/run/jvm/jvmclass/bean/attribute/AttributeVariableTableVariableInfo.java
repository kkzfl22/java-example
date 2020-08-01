package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

/**
 * 一个栈帧中局部变量的关联
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeVariableTableVariableInfo {

  /** 局部变量的生命周期开始的字节码偏移量 */
  private short startPc;

  /** 作用范围覆盖的和蔗 */
  private short length;

  /** 指向常量池常量索引 */
  private short nameIndex;

  /** 指向常量池的局部变量的描述符 */
  private short describeIndex;

  /** 这个局部变量在栈帧的局部变量槽的位置 */
  private short index;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeVariableTableVariableInfo{");
    sb.append("startPc=").append(startPc);
    sb.append(", length=").append(length);
    sb.append(", nameIndex=").append(nameIndex);
    sb.append(", describeIndex=").append(describeIndex);
    sb.append(", index=").append(index);
    sb.append('}');
    return sb.toString();
  }
}
