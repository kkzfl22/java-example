package com.liujun.algorithm.ahoCorasick.constatnt;

import java.util.Arrays;
import java.util.List;

/**
 * 使用ac自动机进行的网页多模式串匹配操作
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/03
 */
public enum AcHtmlTagEnum {

  /** 网页链接href属性 */
  HREF_TAG_START(Arrays.asList("href='", "href=\"", "HREF='", "HREF=\"")),

  /** 网页属性href的结束位置匹配 */
  HREF_TAG_END(Arrays.asList("'", "\"")),

  /** 网页中的div标签 */
  DIV_TAG_START(Arrays.asList("<div", "<DIV")),

  /** 网页中的div标签 */
  DIV_TAG_END(Arrays.asList("</div>", "</DIV>")),

  /** 网页中的html标签 */
  HTML_TAG_START(Arrays.asList("<html", "<HTML")),

  /** 网页中的html标签 */
  HTML_TAG_END(Arrays.asList("</html>", "</HTML>")),
  ;

  private List<String> ackey;

  AcHtmlTagEnum(List<String> ackey) {
    this.ackey = ackey;
  }

  public List<String> getAckey() {
    return ackey;
  }

  /**
   * 获取枚举属性的长度
   *
   * @return 长度信息
   */
  public int getOneLength() {
    return this.ackey.get(0).length();
  }
}
