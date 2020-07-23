package com.liujun.run.jvm.jvmclass.classReader.field;

import com.liujun.run.jvm.jvmclass.bean.fieldtable.Field;
import com.liujun.run.jvm.jvmclass.bean.fieldtable.FieldTable;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeTable;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 字段表的读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class FieldTableReader {

  public static final FieldTableReader INSTANCE = new FieldTableReader();

  /**
   * 字段表集合
   *
   * @param bufferReader 缓冲区
   * @return 字段表信息
   */
  public FieldTable readerFieldTable(ByteBufferOperator bufferReader) {

    short fieldCount = bufferReader.readShort();

    FieldTable fieldTable = new FieldTable(fieldCount);
    int index = 0;

    while (index < fieldCount) {
      fieldTable.getField()[index] = this.readerField(bufferReader);
      index++;
    }

    return fieldTable;
  }

  /**
   * 读取字段信息
   *
   * @param buffer 缓冲区信息
   * @return 字段信息
   */
  private Field readerField(ByteBufferOperator buffer) {
    Field field = new Field();

    field.setAccessFlag(buffer.readShort());
    field.setNameIndex(buffer.readShort());
    field.setDescriptorIndex(buffer.readShort());

    short attributeCount = buffer.readShort();
    AttributeTable attributeInfo = new AttributeTable(attributeCount);

    if (attributeCount != 0) {
      throw new IllegalArgumentException("current not implement");
    }

    field.setAttributeTable(attributeInfo);

    return field;
  }
}
