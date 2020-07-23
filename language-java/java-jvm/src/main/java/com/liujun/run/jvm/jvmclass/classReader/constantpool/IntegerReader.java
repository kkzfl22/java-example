package com.liujun.run.jvm.jvmclass.classReader.constantpool;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.IntegerEntity;
import com.liujun.run.jvm.jvmclass.classReader.ConstantReaderInf;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 按高位在前存储的int类型值
 *
 * @author liujun
 * @version 0.0.1
 */
public class IntegerReader implements ConstantReaderInf {

  public static final IntegerReader INSTANCE = new IntegerReader();

  @Override
  public ConstantBase reader(ByteBufferOperator buffer) {
    IntegerEntity entity = new IntegerEntity();
    entity.setTag(ConstantEnum.CONSTANT_INTEGER_INFO.getTag());
    entity.setBytes(buffer.readInt());

    return entity;
  }
}
