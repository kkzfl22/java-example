package com.liujun.run.jvm.jvmclass.bean.fieldtable;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;

/**
 * 字段表集合
 *
 * <p>字段即为java类中的属性字段
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class FieldTable {

  /** 字段的数量 */
  private short fieldCount;

  /** 属性对象 */
  private Field[] field;

  public FieldTable(short fieldCount) {
    this.fieldCount = fieldCount;
    this.field = new Field[this.fieldCount];
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FieldTable{");
    sb.append("fieldCount=").append(fieldCount);
    sb.append(", field=").append(Arrays.toString(field));
    sb.append('}');
    return sb.toString();
  }
}
