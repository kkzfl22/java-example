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

  /** 类型处理信息 */
  private static final Map<AttributeTypeEnum, AttributeTypeReaderInf> ATTRIBUTE_INSTANCE_MAP =
      new HashMap<>();

  /** 实例信息 */
  public static final AttributeTableReader INSTANCE = new AttributeTableReader();

  static {
    ATTRIBUTE_INSTANCE_MAP.put(AttributeTypeEnum.CODE, AttributeReaderCodeImpl.INSTANCE);
  }

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

        // 1,读取名称
        short nameIndex = buffer.readShort();
        // 获取类型相关的信息
        AttributeTypeEnum typeInfo = this.getType(nameIndex, constantPool);

        if (null != typeInfo) {
          // 进行code的相关属性信息的读取操作
          AttributeBase attributeInfo =
              ATTRIBUTE_INSTANCE_MAP.get(typeInfo).attributeReader(nameIndex, buffer, constantPool);
          attributes.getAttributeBases()[readIndex] = attributeInfo;
        }

        readIndex++;
      }
    }

    return attributes;
  }

  /**
   * 获取类型信息
   *
   * @param index 索引信息
   * @param constantPool 静态常量池
   * @return 返回枚举对象信息
   */
  private AttributeTypeEnum getType(int index, ConstantPool constantPool) {
    // 2,从constant中提取信息
    ConstantBase baseInfo = constantPool.getConstantPool()[index];
    // 3,转换为utf8信息提供
    Utf8Info codeName = (Utf8Info) baseInfo;

    String name = new String(codeName.getBytes());
    AttributeTypeEnum typeInfo = AttributeTypeEnum.getType(name);

    return typeInfo;
  }
}
