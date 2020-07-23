package com.liujun.run.jvm.jvmclass.bean.functiontable;

import lombok.Data;
import lombok.ToString;

/**
 * 方法区信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
@ToString
public class FunctionTable {

  /** 方法区数量 */
  private short functionCount;

  /** 方法表信息 */
  private Function[] functions;

  public FunctionTable(short functionCount) {
    this.functionCount = functionCount;
    this.functions = new Function[this.functionCount];
  }
}
