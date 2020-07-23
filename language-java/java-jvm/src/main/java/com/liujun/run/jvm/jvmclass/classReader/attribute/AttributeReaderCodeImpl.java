package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeCode;
import com.liujun.run.jvm.jvmclass.bean.exception.ExceptionTable;
import com.liujun.run.jvm.jvmclass.classReader.exception.ExceptionReader;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * code的属性相关的读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class AttributeReaderCodeImpl implements AttributeTypeReaderInf {

  public static final AttributeReaderCodeImpl INSTANCE = new AttributeReaderCodeImpl();

  @Override
  public AttributeBase attributeReader(
      short nameIndex, ByteBufferOperator buffer, ConstantPool constantPool) {

    AttributeCode codeAttribute = new AttributeCode();

    codeAttribute.setAttributeNameIndex(nameIndex);
    codeAttribute.setAttributeLength(buffer.readInt());
    codeAttribute.setMaxStack(buffer.readShort());
    codeAttribute.setMaxLocals(buffer.readShort());
    codeAttribute.setCodeLength(buffer.readInt());

    if (codeAttribute.getCodeLength() > 0) {
      byte[] codeArrays = new byte[codeAttribute.getCodeLength()];
      int codeIndex = 0;
      while (codeIndex < codeAttribute.getCodeLength()) {
        codeArrays[codeIndex] = buffer.readU1();
        short dsItem = 0xf8;
        System.out.println(
            "当前指令:" + Integer.toHexString((codeArrays[codeIndex] & ByteBufferOperator.FLAG)));
        codeIndex++;
      }
      // 将字节码指令存储
      codeAttribute.setCode(codeArrays);
    }
    // 异常信息
    codeAttribute.setExceptionTableLength(buffer.readShort());
    if (codeAttribute.getExceptionTableLength() > 0) {
      // 读取异常信息
      ExceptionTable[] exceptionTables =
          ExceptionReader.INSTANCE.reader(codeAttribute.getExceptionTableLength(), buffer);
      codeAttribute.setExceptionInfo(exceptionTables);
    }
    // 读取属性信息
    codeAttribute.setAttributeCount(buffer.readShort());
    if (codeAttribute.getAttributeCount() > 0) {
      // 进行属性信息的读取操作

    }

    return codeAttribute;
  }
}
