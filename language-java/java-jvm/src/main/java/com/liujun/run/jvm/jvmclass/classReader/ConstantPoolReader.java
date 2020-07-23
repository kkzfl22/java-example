package com.liujun.run.jvm.jvmclass.classReader;

import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.classReader.constantpool.*;
import com.liujun.run.jvm.jvmclass.constant.ConstantEnum;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量池中的数据读取
 *
 * @author liujun
 * @version 0.0.1
 */
public class ConstantPoolReader {

  public static final ConstantPoolReader INSTANCE = new ConstantPoolReader();

  private static final Map<Byte, ConstantReaderInf> READ_TYPE_CONSTANT_MAP = new HashMap<>();

  static {
    // 按解析的类型信息将数据放入
    // 数据为UTF8缩略码数据信息
    READ_TYPE_CONSTANT_MAP.put(ConstantEnum.CONSTANT_UTF8_INFO.getTag(), Utf8Reader.INSTANCE);
    // 放入MethodRef的解析器
    READ_TYPE_CONSTANT_MAP.put(
        ConstantEnum.CONSTANT_METHOD_REF_INFO.getTag(), MethodReferenceReader.INSTANCE);
    // 放入fieldRef的解析器
    READ_TYPE_CONSTANT_MAP.put(
        ConstantEnum.CONSTANT_FIELD_REF_INFO.getTag(), FieldRefReader.INSTANCE);
    // 放入class信息解析器
    READ_TYPE_CONSTANT_MAP.put(ConstantEnum.CONSTANT_CLASS_INFO.getTag(), ClassInfoReader.INSTANCE);
    // 名称和类型信息描述符的注入
    READ_TYPE_CONSTANT_MAP.put(
        ConstantEnum.CONSTANT_NAMEANDTYPE_INFO.getTag(), NameAndTypeReader.INSTANCE);
    // int类型的读取
    READ_TYPE_CONSTANT_MAP.put(ConstantEnum.CONSTANT_INTEGER_INFO.getTag(), IntegerReader.INSTANCE);
  }

  /**
   * 数据读取操作
   *
   * @param tag 当前的标识
   * @param buffer 缓冲区
   * @return 返回信息
   */
  public ConstantBase reader(byte tag, ByteBufferOperator buffer) {
    ConstantReaderInf readerInstance = READ_TYPE_CONSTANT_MAP.get(tag);

    if (readerInstance != null) {
      return readerInstance.reader(buffer);
    }

    return null;
  }
}
