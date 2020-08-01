package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

import java.util.Arrays;

/**
 * 用于描述栈帧中局部变量表的变量与java源代码中定义的变量之间的关系
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeVariableTable extends AttributeBase {

  /** 局部变量表长度 */
  private short localVariableTableLength;

  /** 局部变量表的描述信息 */
  private AttributeVariableTableVariableInfo[] variableTables;

  /**
   * 构建以指定大小长度构建栈帧中变量表中变量与源代码中的变量之间关系
   *
   * @param localVariableTableLength 数据项
   */
  public void setLocalVariableTableLength(short localVariableTableLength) {
    this.localVariableTableLength = localVariableTableLength;
    this.variableTables = new AttributeVariableTableVariableInfo[this.localVariableTableLength];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeVariableTable{");
    sb.append("localVariableTableLength=").append(localVariableTableLength);
    sb.append(", variableTables=").append(Arrays.toString(variableTables));
    sb.append('}');
    return sb.toString();
  }
}
