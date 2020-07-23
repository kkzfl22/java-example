package com.liujun.run.jvm.jvmclass.bean.functiontable;

import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeTable;
import com.liujun.run.jvm.jvmclass.constant.FunctionAccessFlagEnum;
import lombok.Data;

import java.util.Arrays;

/**
 * 方法表对象信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class Function {

  /** 方法的访问标志 */
  private short accessFlags;

  /** 名称的在常量区的索引号 */
  private short nameIndex;

  /** 描述符信息在常量区的索引号 */
  private short descriptorIndex;

  /** 属性信息 */
  private AttributeTable attributes;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();

    sb.append("\n\n");
    sb.append("Function{");
    sb.append("accessFlags=")
        .append(accessFlags)
        .append(",(hex-->")
        .append(Integer.toHexString(accessFlags))
        .append("),");
    sb.append("function access Flag :")
        .append(Arrays.toString(FunctionAccessFlagEnum.accessFlags(this.accessFlags)));
    sb.append(", nameIndex=").append(nameIndex);
    sb.append(", descriptorIndex=").append(descriptorIndex);
    sb.append(", attributes=").append(attributes);
    sb.append('}');
    return sb.toString();
  }
}
