package com.liujun.run.jvm.jvmclass.classReader;

import com.liujun.run.jvm.jvmclass.FileChannelReader;
import com.liujun.run.jvm.jvmclass.bean.ConstantPool;
import com.liujun.run.jvm.jvmclass.bean.InterfaceArrayEntity;
import com.liujun.run.jvm.jvmclass.bean.JavaClassEntity;
import com.liujun.run.jvm.jvmclass.bean.constanttype.ConstantBase;
import com.liujun.run.jvm.jvmclass.classReader.field.FieldTableReader;
import com.liujun.run.jvm.jvmclass.classReader.functiontable.FunctionTableReader;
import com.liujun.run.jvm.jvmclass.utils.ByteBufferOperator;

import java.nio.ByteBuffer;

/**
 * @author liujun
 * @version 0.0.1
 */
public class ClassFileReader {

  public static void fileReader(String filePath) {
    FileChannelReader operator = FileChannelReader.getInstance(filePath);

    ByteBuffer readBuffer = operator.getBuffer();
    // 将读取器进行包装
    ByteBufferOperator bufferReader = ByteBufferOperator.getInstance(readBuffer);

    JavaClassEntity javaClass =
        JavaClassEntity.builder()
            // 魔数，JVM能读取的标识
            .magicNumber(bufferReader.readInt())
            // 次版本号
            .minorVersion(bufferReader.readShort())
            // 主版本号
            .majorVersion(bufferReader.readShort())
            // 常量池
            .constantPool(getConstantPool(bufferReader))
            // 访问修饰符
            .accessFlag(bufferReader.readShort())
            // 类索引
            .thisClassIndex(bufferReader.readShort())
            // 父类索引
            .superClassIndex(bufferReader.readShort())
            // 实现的接口集合
            .interfaceArray(getInterfaceArrays(bufferReader))
            // 读取属性信息
            .fieldTable(FieldTableReader.INSTANCE.readerFieldTable(bufferReader))
            .build();

    // 读取方法表
    javaClass.setFunctionTable(
        FunctionTableReader.INSTANCE.readerFunctionTable(
            bufferReader, javaClass.getConstantPool()));

    System.out.println(javaClass);
    System.out.println("=============================================");
  }

  /**
   * 读取实现的接口列表信息
   *
   * @param bufferReader 缓冲区中的数据
   * @return 接口列表信息
   */
  private static InterfaceArrayEntity getInterfaceArrays(ByteBufferOperator bufferReader) {
    // 读取容量
    short arrayLength = bufferReader.readShort();
    InterfaceArrayEntity instanceArray = InterfaceArrayEntity.newInstance(arrayLength);
    int index = 0;
    while (index < arrayLength) {
      instanceArray.getInterfaceIndex()[index] = bufferReader.readShort();
      index++;
    }
    return instanceArray;
  }

  /**
   * 获取常量池的数据信息
   *
   * @param bufferReader 缓冲读取区信息
   * @return 当前的静态常量池对象信息
   */
  private static ConstantPool getConstantPool(ByteBufferOperator bufferReader) {

    // 常量池的第一位是常量池的容量，十六进制为0x0016,十进制22,偏移从1开始，并非从0，代码21项常量，索引范围1-21
    short constantSize = bufferReader.readShort();
    // constantSize = (short) (constantSize - 1);
    ConstantPool constantPool = new ConstantPool(constantSize);

    // 进行循环的读取操作
    int readIndex = 1;
    while (readIndex < constantSize) {
      // 1,读取第一个字节用于得到类型信息
      byte tag = bufferReader.readU1();
      ConstantBase dataInfo = ConstantPoolReader.INSTANCE.reader(tag, bufferReader);
      // 2,得到当前的解析程序
      constantPool.getConstantPool()[readIndex] = dataInfo;
      readIndex++;
    }

    return constantPool;
  }
}
