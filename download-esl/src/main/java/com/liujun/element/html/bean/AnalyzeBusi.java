package com.liujun.element.html.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 网页分析的实体信息
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/20
 */
@Data
@ToString
public class AnalyzeBusi {

  /** 网页地址信息 */
  private String href;

  /** 网页链接文本 */
  private String hrefContext;

  /** 网页中<a标签开始的位置 */
  private int hrefStart;

  /** 网页中</标签结束的位置 */
  private int hrefEnd;

  /** 网页的结束位置 */
  private int EndPostion;

  public AnalyzeBusi(String href) {
    this.href = href;
  }
}
