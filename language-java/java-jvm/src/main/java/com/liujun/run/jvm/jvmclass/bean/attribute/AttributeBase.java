package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

/**
 * 基础属性信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeBase {

  /** 属性名称索引 */
  private short attributeNameIndex;

  /** 属性长度 */
  private int attributeLength;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeBase{");
    sb.append("attributeNameIndex=").append(attributeNameIndex);
    sb.append(", attributeLength=").append(attributeLength);
    sb.append('}');
    return sb.toString();
  }
}
