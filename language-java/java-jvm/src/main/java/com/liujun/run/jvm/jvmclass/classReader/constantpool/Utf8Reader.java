package com.liujun.run.jvm.jvmclass.classReader.constantpool;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.bean.constanttype.base.Utf8Info;
import com.liujun.run.jvm.jvmclass.classReader.ConstantReaderInf;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

/**
 * 方法及类的描述信息
 *
 * @author liujun
 * @version 0.0.1
 */
public class Utf8Reader implements ConstantReaderInf {

  public static final Utf8Reader INSTANCE = new Utf8Reader();

  @Override
  public ConstantBase reader(ByteBufferOperator buffer) {

    Utf8Info utf8Info = new Utf8Info();
    utf8Info.setTag(ConstantEnum.CONSTANT_UTF8_INFO.getTag());
    utf8Info.setLength(buffer.readShort());
    utf8Info.setBytes(buffer.readLength(utf8Info.getLength()));

    return utf8Info;
  }
}
