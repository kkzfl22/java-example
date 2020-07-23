package com.liujun.run.jvm.jvmclass.bean.exception;

import lombok.Data;

/**
 * @author liujun
 * @version 0.0.1
 */
@Data
public class ExceptionTable {

  /** 开始的行 */
  private short startPc;

  /** 结束行 */
  private short endPc;

  /** 处理异常的行 */
  private short handlerPc;

  /** 处理异常的类型 */
  private short catchType;





  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ExceptionTable{");
    sb.append("startPc=").append(startPc);
    sb.append(", endPc=").append(endPc);
    sb.append(", handlerPc=").append(handlerPc);
    sb.append(", catchType=").append(catchType);
    sb.append('}');
    return sb.toString();
  }
}
