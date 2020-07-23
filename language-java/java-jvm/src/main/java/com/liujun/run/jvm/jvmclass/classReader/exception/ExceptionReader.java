package com.liujun.run.jvm.jvmclass.classReader.exception;

import com.liujun.run.jvm.jvmclass.bean.exception.ExceptionTable;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 异常信息读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class ExceptionReader {

  public static final ExceptionReader INSTANCE = new ExceptionReader();

  /**
   * 异常信息读取
   *
   * @param buffer
   * @return
   */
  public ExceptionTable[] reader(short exceptionLength, ByteBufferOperator buffer) {

    if (exceptionLength <= 0) {
      return new ExceptionTable[0];
    }

    ExceptionTable[] exceptions = new ExceptionTable[exceptionLength];

    short readIndex = 0;
    while (readIndex < exceptionLength) {
      // 异常信息读取
      exceptions[readIndex] = this.readException(buffer);
      readIndex++;
    }

    return exceptions;
  }

  private ExceptionTable readException(ByteBufferOperator buffer) {
    ExceptionTable item = new ExceptionTable();
    item.setStartPc(buffer.readShort());
    item.setEndPc(buffer.readShort());
    item.setHandlerPc(buffer.readShort());
    item.setCatchType(buffer.readShort());
    return item;
  }
}
