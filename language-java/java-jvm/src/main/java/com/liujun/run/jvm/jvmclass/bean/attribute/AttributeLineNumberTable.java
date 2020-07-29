package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

import java.util.Arrays;

/**
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeLineNumberTable extends AttributeBase {

  /** 当前所包含行号的信息 */
  private short lineNumberTableLength;

  /** 字节行号与源代码的对应关联 */
  private AttributeLineNumberTableNumberInfo[] lineNumberInfo;

  /**
   * 构建以指定大小长度的行号与源代码长度信息
   *
   * @param lineNumberTableLength 数据项
   */
  public void setAttributeLineNumberTable(short lineNumberTableLength) {
    this.lineNumberTableLength = lineNumberTableLength;
    this.lineNumberInfo = new AttributeLineNumberTableNumberInfo[this.lineNumberTableLength];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeLineNumberTable{");
    sb.append("lineNumberTableLength=").append(lineNumberTableLength);
    sb.append(", lineNumberInfo=").append(Arrays.toString(lineNumberInfo));
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
