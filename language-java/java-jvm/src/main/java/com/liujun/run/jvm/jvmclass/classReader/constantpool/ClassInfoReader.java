package com.liujun.run.jvm.jvmclass.classReader.constantpool;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ClassEntity;
import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.classReader.ConstantReaderInf;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * class信息的读取器
 *
 * @author liujun
 * @version 0.0.1
 */
public class ClassInfoReader implements ConstantReaderInf {

  public static final ClassInfoReader INSTANCE = new ClassInfoReader();

  @Override
  public ConstantBase reader(ByteBufferOperator buffer) {

    ClassEntity classInfo = new ClassEntity();

    classInfo.setTag(ConstantEnum.CONSTANT_CLASS_INFO.getTag());
    // 设置类信息
    classInfo.setNameIndex(buffer.readShort());

    return classInfo;
  }
}
