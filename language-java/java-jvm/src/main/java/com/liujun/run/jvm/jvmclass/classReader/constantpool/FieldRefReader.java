package com.liujun.run.jvm.jvmclass.classReader.constantpool;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.FieldRefEntity;
import com.liujun.run.jvm.jvmclass.classReader.ConstantReaderInf;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 进行静态常量池中的field属性数据读取操作
 *
 * @author liujun
 * @version 0.0.1
 */
public class FieldRefReader implements ConstantReaderInf {

  public static final FieldRefReader INSTANCE = new FieldRefReader();

  @Override
  public ConstantBase reader(ByteBufferOperator buffer) {

    FieldRefEntity fieldRef = new FieldRefEntity();

    // 设置标识符
    fieldRef.setTag(ConstantEnum.CONSTANT_FIELD_REF_INFO.getTag());
    // 设置读取到的class索引
    fieldRef.setConstantClassInfoIndex(buffer.readShort());
    // 设置读取到的nameAndType
    fieldRef.setConstantNameAndTypeIndex(buffer.readShort());

    return fieldRef;
  }
}
