package com.liujun.element.html.bean;

import lombok.Data;

/**
 * 网页信息
 *
 * @author liujun
 * @version 0.0.1
 */
@Data
public class HtmlData {

  /** 网页标题信息 */
  private String title;

  /** 网页内容信息 */
  private String context;

  /** 音频地址 */
  private String audioHref;

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("HtmlData{");
    sb.append("title='").append(title).append('\'');
    sb.append(", context='").append(context).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
