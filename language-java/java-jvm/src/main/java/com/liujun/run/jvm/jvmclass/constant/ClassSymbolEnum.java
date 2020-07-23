package com.liujun.run.jvm.jvmclass.constant;

/**
 * 描述符标识字符含义
 *
 * @author liujun
 * @version 0.0.1
 */
public enum ClassSymbolEnum {

  /** 表示java的byte类型 */
  BYTE('B', "byte"),

  /** 基本类型char */
  CHAR('C', "char"),

  /** 基本类型double */
  DOUBLE('D', "double"),

  /** 基本类型float */
  FLOAT('F', "float"),

  /** 基本类型int */
  INT('I', "int"),

  /** 基本类型long */
  LONG('J', "long"),

  /** 基本类型short */
  SHORT('S', "short"),

  /** 无返回值 */
  VOID('V', "void"),

  /** java的对象类型 */
  OBJECT('L', "java/lang/Object"),

  /** 数组的表示 */
  ARRAY('[', "array");

  private char symbol;

  private String javaType;

  ClassSymbolEnum(char symbol, String javaType) {
    this.symbol = symbol;
    this.javaType = javaType;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ClassSymbolEnum{");
    sb.append("symbol=").append(symbol);
    sb.append(", javaType='").append(javaType).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
