package com.liujun.run.jvm.jvmclass.bean.constanttype;

import lombok.Data;

/**
 * 名称和类型的实体信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class NameAndTypeEntity extends ConstantBase {

  /** 名称的索引项 */
  private short nameIndex;

  /** 描述符的索引 */
  private short describeIndex;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("NameAndTypeEntity{");
    sb.append("nameIndex=").append(nameIndex);
    sb.append(", describeIndex=").append(describeIndex);
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
