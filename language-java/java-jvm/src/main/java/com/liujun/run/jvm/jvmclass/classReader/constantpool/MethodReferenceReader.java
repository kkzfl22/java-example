package com.liujun.run.jvm.jvmclass.classReader.constantpool;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.MethodRefEntity;
import com.liujun.run.jvm.jvmclass.classReader.ConstantReaderInf;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 方法及类的描述信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class MethodReferenceReader implements ConstantReaderInf {

  public static final MethodReferenceReader INSTANCE = new MethodReferenceReader();

  @Override
  public ConstantBase reader(ByteBufferOperator buffer) {

    MethodRefEntity methodRef = new MethodRefEntity();

    // 标识符
    methodRef.setTag(ConstantEnum.CONSTANT_METHOD_REF_INFO.getTag());
    // 1,当前的class的索引位置
    methodRef.setConstantClassInfoIndex(buffer.readShort());
    // 当前的nameAndType的索引位置
    methodRef.setConstantNameAndTypeIndex(buffer.readShort());

    return methodRef;
  }
}
