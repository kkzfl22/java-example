package com.liujun.run.jvm.jvmclass.classReader;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/** 资源池数据读取接口 */
public interface ConstantReaderInf {

  /**
   * 读取相关的字节码接口
   *
   * @param buffer 字节信息读取器
   * @return 转换的实体对象
   */
  ConstantBase reader(ByteBufferOperator buffer);
}
