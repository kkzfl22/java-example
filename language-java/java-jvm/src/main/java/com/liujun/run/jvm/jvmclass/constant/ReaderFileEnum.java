package com.liujun.run.jvm.jvmclass.constant;

/**
 * 文件的枚举定义信息
 *
 * @author liujun
 * @version 0.0.1
 */
public enum ReaderFileEnum {
  SOURCE_CLASS_FILE("JavaClassFile.class"),

  SOURCE_INTERFACE_DATAINF("DataInf.class"),

  SOURCE_IMPLEMENT_DATACOUNTIMP("DataCountImpl.class"),
  ;

  private String readFile;

  private static final String BASE_PATH = "./classout/";

  ReaderFileEnum(String readFile) {
    this.readFile = readFile;
  }

  public String getReadFile() {
    return BASE_PATH + readFile;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ReaderFileEnum{");
    sb.append("readFile='").append(readFile).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
