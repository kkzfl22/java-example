package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.*;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 局部变量表与java源代码中定义的变量之间的关系
 *
 * @author liujun
 * @version 0.0.1
 */
public class AttributeLocalVariableTableImpl implements AttributeTypeReaderInf {

  public static AttributeLocalVariableTableImpl INSTANCE = new AttributeLocalVariableTableImpl();

  @Override
  public AttributeBase attributeReader(
      short nameIndex, ByteBufferOperator buffer, ConstantPool constantPool) {

    AttributeVariableTable variableTable = new AttributeVariableTable();
    variableTable.setAttributeNameIndex(nameIndex);
    variableTable.setAttributeLength(buffer.readInt());
    variableTable.setLocalVariableTableLength(buffer.readShort());

    int variableIndex = 0;
    while (variableIndex < variableTable.getLocalVariableTableLength()) {
      int readLength = 0;
      AttributeVariableTableVariableInfo variableInfo = new AttributeVariableTableVariableInfo();

      variableInfo.setStartPc(buffer.readShort());
      readLength++;
      variableInfo.setLength(buffer.readShort());
      readLength++;
      variableInfo.setNameIndex(buffer.readShort());
      readLength++;
      variableInfo.setDescribeIndex(buffer.readShort());
      readLength++;
      variableInfo.setIndex(buffer.readShort());
      readLength++;

      // 如果长度不相同，则继续读取
      if (readLength != variableInfo.getLength()) {
        int readDataLength = variableInfo.getLength() - readLength;
        buffer.readLength(readDataLength);
      }
      variableTable.getVariableTables()[variableIndex] = variableInfo;
      variableIndex++;
    }

    return variableTable;
  }
}
