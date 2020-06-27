package com.liujun.constant;

/**
 * @author liujun
 * @version 0.0.1
 */
public enum TestFileName {

  /** 首页文件 */
  INDEX("esl_english_index.html"),

  /** 孩子学英语每一集 */
  CHILDREN_1("esl_english_children1.html"),

  /** 去动物园首页 */
  TO_THE_ZOO("esl_english_gotothezoo.html"),

  /** 测试圣诞节的读取 */
  CHRISTMAS_TIME("esl_english_christmas.html"),

  HE_TAKE_TEST("esl_english_hetakesTest.html"),

  EXERCISINGT("esl_english_exercisingt.html"),

  MOST_POPULAR_50("50MostPopularAmericanLastNames.html"),

  MOST_POPULAR_100("100MostPopularAmericanLastNames.html"),

  /** 带中文的网页 */
  HTML_CHINA("esl_english_china.html"),

  /** 带中文的网页 */
  HOTEL("esl_english_hotel.html"),

  /** 编码特殊的网页 */
  ENGLISH_FOR_SPECIFIC_PURPOSES("CharsetEnglishforSpecificPurposes.html");

  /** 文件名 */
  private String fileName;

  TestFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return fileName;
  }
}
