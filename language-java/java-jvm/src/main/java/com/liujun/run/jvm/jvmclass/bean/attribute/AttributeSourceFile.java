package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

import java.util.Arrays;

/**
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeSourceFile extends AttributeBase {

  /** 指向常量池中Constant的UTF8型的常量索引 */
  private short sourceFileIndex;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeSourceFile{");
    sb.append("sourceFileIndex=").append(sourceFileIndex);
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
