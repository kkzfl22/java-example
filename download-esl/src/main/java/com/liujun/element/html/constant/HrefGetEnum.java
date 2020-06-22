package com.liujun.element.html.constant;

/**
 * 进行链接的枚举获取
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/31
 */
public enum HrefGetEnum {
  /** 网页内容信息 */
  HTML_CONTEXT_BYTES("href_context_bytes"),

  /** 当前网页的输入链接 */
  HTML_INPUT_CURR_HTML_HREF("href_input_curr_href"),

  /** 起始搜索的顶点 */
  HREF_A_START_POSITION("href_start_position"),

  /** 搜索的标签内容<a 标签的位置 */
  HREF_CON_A_START_POSITION("href_con_a_start_position"),

  /** 用来进行过滤的标签范围 */
  HREF_FILTER_SCOPE("href_filter_scope_index"),

  /** 数据返回对象信息 */
  HREF_RESULT_OBJECT("href_result_object"),

  /** 存储返回的链接地址集合 */
  HREF_RESULT_SET_OBJECT("href_reslut_set_object"),
  ;

  private String hrefKey;

  HrefGetEnum(String hrefKey) {
    this.hrefKey = hrefKey;
  }

  public String getHrefKey() {
    return hrefKey;
  }
}
