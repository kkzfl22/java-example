package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeLineNumberTable;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeLineNumberTableNumberInfo;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 实现行号相关联的数据读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class AttributeLineNumberTableImpl implements AttributeTypeReaderInf {

  public static AttributeLineNumberTableImpl INSTANCE = new AttributeLineNumberTableImpl();

  @Override
  public AttributeBase attributeReader(
      short nameIndex, ByteBufferOperator buffer, ConstantPool constantPool) {

    AttributeLineNumberTable lineNumberTable = new AttributeLineNumberTable();
    lineNumberTable.setAttributeNameIndex(nameIndex);
    lineNumberTable.setAttributeLength(buffer.readInt());
    // 读取u2的长度
    lineNumberTable.setLineNumberTableLength(buffer.readShort());

    if (lineNumberTable.getLineNumberTableLength() > 0) {
      lineNumberTable.setAttributeLineNumberTable(lineNumberTable.getLineNumberTableLength());
      // 进行相关数据的行号与字节关联的信息
      int readIndex = 0;
      while (readIndex < lineNumberTable.getLineNumberTableLength()) {
        lineNumberTable.getLineNumberInfo()[readIndex] = new AttributeLineNumberTableNumberInfo();
        lineNumberTable.getLineNumberInfo()[readIndex].setStartPc(buffer.readShort());
        lineNumberTable.getLineNumberInfo()[readIndex].setLineNumber(buffer.readShort());
        readIndex++;
      }
    }
    return lineNumberTable;
  }
}
