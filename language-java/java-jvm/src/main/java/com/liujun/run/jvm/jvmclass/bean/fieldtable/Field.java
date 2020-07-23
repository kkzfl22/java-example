package com.liujun.run.jvm.jvmclass.bean.fieldtable;

import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeTable;
import com.liujun.run.jvm.jvmclass.constant.FieldAccessFlagEnum;
import lombok.Data;

import java.util.Arrays;

/**
 * @author liujun
 * @version 0.0.1
 */
@Data
public class Field {

  /** 属性的访问标识 */
  private short accessFlag;
  /** 属性名称在常量池的索引，使用u2类型 */
  private short nameIndex;
  /** 全限定名的常量池索引，使用u2类型 */
  private short descriptorIndex;

  /** 读取的属性信息 */
  private AttributeTable attributeTable;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Field{");
    sb.append("accessFlag=")
        .append(accessFlag)
        .append(",(hex-->")
        .append(Integer.toHexString(accessFlag))
        .append("),");
    sb.append(Arrays.toString(FieldAccessFlagEnum.accessFlags(accessFlag))).append("\n");
    sb.append(", nameIndex=").append(nameIndex);
    sb.append(", descriptorIndex=").append(descriptorIndex);
    sb.append(", attributeTable=").append(attributeTable);
    sb.append('}');
    return sb.toString();
  }
}
