package com.liujun.run.jvm.jvmclass.classReader.attribute;

import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeBase;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeCode;
import com.liujun.run.jvm.jvmclass.bean.attribute.AttributeTable;
import com.liujun.run.jvm.jvmclass.bean.exception.ExceptionTable;
import com.liujun.run.jvm.jvmclass.classReader.exception.ExceptionReader;
import com.liujun.run.jvm.jvmclass.constant.ByteCodeCommand;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

import java.util.Arrays;

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
      int codeLength = 0;
      while (codeLength < codeAttribute.getCodeLength()) {
        codeArrays[codeIndex] = buffer.readU1();
        codeLength++;

        // 获得指令信息
        ByteCodeCommand command = ByteCodeCommand.getCommand(codeArrays[codeIndex]);

        // 检查当前是否需要读取参数
        if (command.getParamLength() > 0) {
          // 按指定的位数读取数据
          byte[] readData = buffer.readLength(command.getParamLength());
          codeLength += command.getParamLength();

          // 数据的参数
          System.out.println(Arrays.toString(readData));
        }

        System.out.println("指令信息:" + command);
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
    // 读取属性的个数
    codeAttribute.setAttributeCount(buffer.readShort());
    if (codeAttribute.getAttributeCount() > 0) {
      int attributeIndex = 0;
      AttributeBase[] attributes = new AttributeBase[codeAttribute.getAttributeCount()];

      while (attributeIndex < codeAttribute.getAttributeCount()) {
        // 1,读取索引号
        attributes[attributeIndex] = AttributeReader.INSTANCE.reader(buffer, constantPool);
        attributeIndex++;
      }
      codeAttribute.setAttribute(attributes);
    }
    // 读取javasource类的信息

    return codeAttribute;
  }
}
