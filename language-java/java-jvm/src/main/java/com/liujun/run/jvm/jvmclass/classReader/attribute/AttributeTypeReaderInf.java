package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 属性按类型进行读取信息
 *
 * @author liujun
 * @version 0.0.1
 */
public interface AttributeTypeReaderInf {

  /**
   * 属性读取方法，
   *
   * @param nameIndex 属性的名称在常量池中的位置
   * @param buffer 缓冲区信息
   * @param constantPool 静态常量池
   * @return 按类型读取后的实体信息
   */
  AttributeBase attributeReader(
      short nameIndex, ByteBufferOperator buffer, ConstantPool constantPool);
}
