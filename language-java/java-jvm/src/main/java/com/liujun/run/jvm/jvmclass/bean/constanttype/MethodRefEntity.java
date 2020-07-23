package com.liujun.run.jvm.jvmclass.bean.constanttype;

import lombok.Data;

/**
 * @author liujun
 * @version 0.0.1
 */
@Data
public class MethodRefEntity extends ConstantBase {

  /** 指向方法的类描述符，CONSTANT_Class_info的索引项 */
  private short constantClassInfoIndex;

  /** 指向名称及类描述符，CONATANT_NameAndType的索引项 */
  private short constantNameAndTypeIndex;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MethodRefEntity{");
    sb.append("constantClassInfoIndex=").append(constantClassInfoIndex);
    sb.append(", constantNameAndTypeIndex=").append(constantNameAndTypeIndex);
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
