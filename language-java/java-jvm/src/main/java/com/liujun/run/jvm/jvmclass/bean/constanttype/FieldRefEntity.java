package com.liujun.run.jvm.jvmclass.bean.constanttype;

import lombok.Data;

/**
 * 声明字段的类或者接口描述符及指向描述符的索引项
 * @author liujun
 * @version 0.0.1
 */
@Data
public class FieldRefEntity extends ConstantBase {

  /** 指向方法的类描述符，CONSTANT_Class_info的索引项 ，一般为父级索引项 */
  private short constantClassInfoIndex;

  /** 指向名称及类描述符，CONATANT_NameAndType的索引项,一般为父级的索引项 */
  private short constantNameAndTypeIndex;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FieldRefEntity{");
    sb.append("constantClassInfoIndex=").append(constantClassInfoIndex);
    sb.append(", constantNameAndTypeIndex=").append(constantNameAndTypeIndex);
    sb.append('}');
    sb.append(super.toString());
    return sb.toString();
  }
}
