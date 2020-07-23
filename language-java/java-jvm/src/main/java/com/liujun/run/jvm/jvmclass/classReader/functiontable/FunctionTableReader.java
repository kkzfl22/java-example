package com.liujun.run.jvm.jvmclass.classReader.functiontable;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.functiontable.Function;
import com.liujun.run.jvm.jvmclass.bean.functiontable.FunctionTable;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeTable;
import com.liujun.run.jvm.jvmclass.classReader.attribute.AttributeTableReader;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 方法表的读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class FunctionTableReader {

  public static final FunctionTableReader INSTANCE = new FunctionTableReader();

  /**
   * 字段表集合
   *
   * @param bufferReader 缓冲区
   * @param constantPool 常量池
   * @return 字段表信息
   */
  public FunctionTable readerFunctionTable(
      ByteBufferOperator bufferReader, ConstantPool constantPool) {

    short fieldCount = bufferReader.readShort();

    FunctionTable fieldTable = new FunctionTable(fieldCount);
    int index = 0;

    while (index < fieldCount) {
      fieldTable.getFunctions()[index] = this.readerField(bufferReader, constantPool);
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
  private Function readerField(ByteBufferOperator buffer, ConstantPool constantPool) {
    Function field = new Function();

    field.setAccessFlags(buffer.readShort());
    field.setNameIndex(buffer.readShort());
    field.setDescriptorIndex(buffer.readShort());

    short attributeCount = buffer.readShort();
    if (attributeCount > 0) {
      System.out.println("需要进行属性表数据读取");
      AttributeTableReader.INSTANCE.readerFieldTable(attributeCount, buffer, constantPool);
    }
    // field.setAttributes(attributes);

    return field;
  }
}
