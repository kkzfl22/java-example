package com.liujun.constant;

/**
 * @author liujun
 * @version 0.0.1
 */
public enum TestFileName {

  /** 首页文件 */
  INDEX("esl_english_inex.html"),

  /** 孩子学英语每一集 */
  CHILDREN_1("esl_english_children1.html"),

  /** 去动物园首页 */
  TO_THE_ZOO("esl_english_gotothezoo.html"),

  /** 测试圣诞节的读取 */
  CHRISTMAS_TIME("esl_english_christmas.html"),

  HE_TAKE_TEST("esl_english_hetakesTest.html"),

  EXERCISINGT("esl_english_exercisingt.html"),
  ;

  /** 文件名 */
  private String fileName;

  TestFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }
}
