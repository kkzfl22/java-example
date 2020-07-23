package com.liujun.run.jvm.jvmclass.bean.attribute;

import lombok.Data;

import java.util.Arrays;

/**
 * 属性表信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class AttributeTable {

  /** 属性索引 */
  private short attributeCount;

  /** 属性表中的信息 */
  private AttributeBase[] attributeBases;

  public AttributeTable(short count) {
    this.attributeCount = count;
    this.attributeBases = new AttributeBase[this.attributeCount];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AttributeTable{");
    sb.append("attributeCount=").append(attributeCount);
    sb.append(", attributes=").append(Arrays.toString(attributeBases));
    sb.append('}');
    return sb.toString();
  }
}
