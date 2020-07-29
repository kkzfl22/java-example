package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

/**
 * 行号信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeLineNumberTableNumberInfo {

  /** 字节码行号 */
  private short startPc;

  /** java的源代码行号 */
  private short lineNumber;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeLineNumberTableNumberInfo{");
    sb.append("startPc=hex(").append(Integer.toHexString(startPc)).append(")-->").append(startPc);
    sb.append(", lineNumber=hex(")
        .append(Integer.toHexString(lineNumber))
        .append(")-->")
        .append(lineNumber);
    sb.append('}');
    return sb.toString();
  }
}
