package com.liujun.run.jvm.jvmclass.bean.constanttype;

import lombok.Data;

/**
 * 常量池公共实体
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class ConstantBase {

  /**
   * 项目类型：
   *
   * <p>1：UTF-8编码的字符串
   *
   * <p>好像类型的枚举在ConstantEnum文件中定义
   */
  protected byte tag;


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ConstantBase{");
    sb.append("tag=hex(").append(Integer.toHexString(tag)).append(")-->").append(tag).append(",");
    sb.append('}');
    return sb.toString();
  }
}
