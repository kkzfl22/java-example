package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeSourceFile;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeVariableTable;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeVariableTableVariableInfo;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 用于记录下生成这个Class文件的源码文件名称
 *
 * @author liujun
 * @version 0.0.1
 */
public class AttributeSourceFileImpl implements AttributeTypeReaderInf {

  public static AttributeSourceFileImpl INSTANCE = new AttributeSourceFileImpl();

  @Override
  public AttributeBase attributeReader(
      short nameIndex, ByteBufferOperator buffer, ConstantPool constantPool) {

    AttributeSourceFile sourceFile = new AttributeSourceFile();

    sourceFile.setAttributeNameIndex(nameIndex);
    sourceFile.setAttributeLength(buffer.readInt());
    sourceFile.setSourceFileIndex(buffer.readShort());

    return sourceFile;
  }
}
