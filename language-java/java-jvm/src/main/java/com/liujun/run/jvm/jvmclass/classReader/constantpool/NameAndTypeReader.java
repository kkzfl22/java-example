package com.liujun.run.jvm.jvmclass.classReader.constantpool;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.NameAndTypeEntity;
import com.liujun.run.jvm.jvmclass.classReader.ConstantReaderInf;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 名称和类型信息读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class NameAndTypeReader implements ConstantReaderInf {

  public static final NameAndTypeReader INSTANCE = new NameAndTypeReader();

  @Override
  public ConstantBase reader(ByteBufferOperator buffer) {

    NameAndTypeEntity nameAndType = new NameAndTypeEntity();

    // 设置标识符
    nameAndType.setTag(ConstantEnum.CONSTANT_NAMEANDTYPE_INFO.getTag());
    // 名称索引符号
    nameAndType.setNameIndex(buffer.readShort());
    // 描述符
    nameAndType.setDescribeIndex(buffer.readShort());

    return nameAndType;
  }
}
