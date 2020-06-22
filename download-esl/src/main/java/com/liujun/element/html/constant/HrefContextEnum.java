package com.liujun.element.html.constant;

/**
 * 进行链接的枚举
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/20
 */
public enum HrefContextEnum {

  /** 原始待处理的字符串 */
  HREF_SRC("href_src"),

  /** 处理中输出的字符串 */
  HREF_OUT("href_out"),

  /** 网页的链接地址 */
  HREF_HTML_URL("href_html_url"),
  ;

  HrefContextEnum(String key) {
    this.key = key;
  }

  /** key信息 */
  private String key;

  public String getKey() {
    return key;
  }
}
