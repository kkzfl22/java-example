package com.liujun.run.jvm.jvmclass.bean.constanttype;

import lombok.Data;

/**
 * 当前类信息的描述
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class ClassEntity extends ConstantBase {

  /** 常量池中的索引值 */
  private short nameIndex;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ClassInfo{");
    sb.append("nameIndex=").append(nameIndex);
    sb.append('}');
    return sb.toString();
  }
}
