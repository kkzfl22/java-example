package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.base.Utf8Info;
import com.liujun.run.jvm.jvmclass.constant.AttributeTypeEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

import java.util.HashMap;
import java.util.Map;

/**
 * 虚拟机规范定义的属性读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class AttributeReader {

  /** 类型处理信息 */
  private static final Map<AttributeTypeEnum, AttributeTypeReaderInf> ATTRIBUTE_INSTANCE_MAP =
      new HashMap<>();

  /** 相关属性的实例信息 */
  public static final AttributeReader INSTANCE = new AttributeReader();

  static {
    // java编译成的字节码指令
    ATTRIBUTE_INSTANCE_MAP.put(AttributeTypeEnum.CODE, AttributeReaderCodeImpl.INSTANCE);
    // 行号与字节的关联
    ATTRIBUTE_INSTANCE_MAP.put(
        AttributeTypeEnum.LINENUMBER_TABLE, AttributeLineNumberTableImpl.INSTANCE);

    // 局部变量表与java源代码中定义的变量之间的关系
    ATTRIBUTE_INSTANCE_MAP.put(
        AttributeTypeEnum.LOCAL_VARIABLE_TABLE, AttributeLocalVariableTableImpl.INSTANCE);

    // java源代码文件信息
    ATTRIBUTE_INSTANCE_MAP.put(AttributeTypeEnum.SOURCE_FILE, AttributeSourceFileImpl.INSTANCE);
  }

  /**
   * 数据的读取操作
   *
   * @param buffer 缓冲区信息
   * @param constantPool 常量池信息
   * @return 读取的属性信息
   */
  public AttributeBase reader(ByteBufferOperator buffer, ConstantPool constantPool) {
    // 1,读取名称
    short nameIndex = buffer.readShort();

    // 获取类型相关的信息
    AttributeTypeEnum typeInfo = this.getType(nameIndex, constantPool);

    if (null != typeInfo) {
      // 进行code的相关属性信息的读取操作
      AttributeBase attributeInfo =
          ATTRIBUTE_INSTANCE_MAP.get(typeInfo).attributeReader(nameIndex, buffer, constantPool);
      return attributeInfo;
    }
    return null;
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
