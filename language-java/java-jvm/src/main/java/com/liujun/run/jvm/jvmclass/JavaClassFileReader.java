package com.liujun.run.jvm.jvmclass;

import com.liujun.run.jvm.jvmclass.classReader.ClassFileReader;
import com.liujun.run.jvm.jvmclass.constant.ReaderFileEnum;

/**
 * 使用java文件读取java字节码文件
 *
 * @author liujun
 * @version 0.0.1
 */
public class JavaClassFileReader {

  public static void main(String[] args) {
    ClassFileReader.fileReader(ReaderFileEnum.SOURCE_CLASS_FILE.getReadFile());
    // ClassFileReader.fileReader(ReaderFileEnum.SOURCE_INTERFACE_DATAINF.getReadFile());
    // ClassFileReader.fileReader(ReaderFileEnum.SOURCE_IMPLEMENT_DATACOUNTIMP.getReadFile());
  }
}
