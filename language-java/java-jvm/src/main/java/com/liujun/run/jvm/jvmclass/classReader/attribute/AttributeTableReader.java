package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeTable;
import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.base.Utf8Info;
import com.liujun.run.jvm.jvmclass.constant.AttributeTypeEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

import java.util.HashMap;
import java.util.Map;

/**
 * 属性表数据读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class AttributeTableReader {

  /** 实例信息 */
  public static final AttributeTableReader INSTANCE = new AttributeTableReader();

  /**
   * 属性表数据
   *
   * @param attributeSize 属性对象大小
   * @param buffer 缓冲区
   * @param constantPool 常量池信息
   * @return 字段表信息
   */
  public AttributeTable readerFieldTable(
      short attributeSize, ByteBufferOperator buffer, ConstantPool constantPool) {

    AttributeTable attributes = new AttributeTable(attributeSize);
    if (attributeSize > 0) {
      // 1,读取名称
      short readIndex = 0;

      while (readIndex < attributeSize) {
        // 进行相关属性的读取操作
        AttributeBase attributeInfo = AttributeReader.INSTANCE.reader(buffer, constantPool);
        attributes.getAttributeBases()[readIndex] = attributeInfo;
        readIndex++;
      }
    }

    return attributes;
  }
}
