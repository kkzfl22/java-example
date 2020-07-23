package com.liujun.run.jvm.jvmclass.constant;

/**
 * 常量池的项目类型枚举
 *
 * @author liujun
 * @version 0.0.1
 */
public enum ConstantEnum {

  /** UTF-8编码的字符串 */
  CONSTANT_UTF8_INFO((byte) 1),

  /** 整形字面量 */
  CONSTANT_INTEGER_INFO((byte) 3),

  /** 浮点数字面量 */
  CONSTANT_FLOAT_INFO((byte) 4),

  /** 长整型字面量 */
  CONSTANT_LONG_INFO((byte) 5),

  /** 双精度浮点数字面量 */
  CONSTANT_DOUBLE_INFO((byte) 6),

  /** 类或者接口的符号引用 */
  CONSTANT_CLASS_INFO((byte) 7),

  /** 字符串类型字面量 */
  CONSTANT_STRING_INFO((byte) 8),

  /** 字段的符号引用 */
  CONSTANT_FIELD_REF_INFO((byte) 9),

  /** 类中方法的描述符引用 */
  CONSTANT_METHOD_REF_INFO((byte) 10),

  /** 接口中方法的符号引用 */
  CONSTANT_INTERFACE_METHOD_REF_INFO((byte) 11),

  /** 字段或方法的部分符号引用 */
  CONSTANT_NAMEANDTYPE_INFO((byte) 12),

  /** 方法的句柄 */
  CONSTANT_METHOD_HANDLE_INFO((byte) 15),

  /** 方法的类型 */
  CONSTANT_METHODTYPE_INFO((byte) 16),

  /** 一个动态计算的常量 */
  CONSTANT_DYNAMIC_INFO((byte) 17),

  /** 动态方法调用点 */
  CONSTANT_INVOKE_DYNAMIC_INFO((byte) 18),

  /** 表示一个模块 */
  CONSTANT_MODULE_INFO((byte) 19),

  /** 一个模块开放或者导出的包 */
  CONSTANT_PACKAGE_INFO((byte) 20),
  ;

  ConstantEnum(byte tag) {
    this.tag = tag;
  }

  private byte tag;

  public byte getTag() {
    return tag;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ConstantEnum{");
    sb.append("tag=").append(tag);
    sb.append('}');
    return sb.toString();
  }
}
