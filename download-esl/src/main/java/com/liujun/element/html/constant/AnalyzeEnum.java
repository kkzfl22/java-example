package com.liujun.element.html.constant;

/**
 * 网页分析流程
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/13
 */
public enum AnalyzeEnum {

  /** 网页分析的内容，分拆为char数组 */
  ANALYZE_INPUT_HTMLCONTEXT_ARRAY("analyze_input_htmlcontext_array"),

  /** 当前网页的链接 */
  ANALYZE_INPUT_CURR_HTML_HREF("analyze_input_curr_html_href"),

  /** 网页内容bean */
  ANALYZE_OUTPUT_DATA_BEAN("analyze_outou_data_bean");

  private String key;

  AnalyzeEnum(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
