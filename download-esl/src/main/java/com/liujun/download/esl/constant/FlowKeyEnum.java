package com.liujun.download.esl.constant;

/**
 * 进行采集分析流程的枚举
 *
 * @author liujun
 * @version 0.0.1
 * @date 2019/03/18
 */
public enum FlowKeyEnum {

  /** 网页入口参数 */
  WEB_ENTRY("web_entry_url"),

  /** 下载的地址 */
  FLOW_DOWNLOAD_ADDRESS("flow_download_url"),

  /** 下载的网页内容信息 */
  FLOW_DOWNLOAD_CONTEXT("flow_download_context"),

  /** 下载后的java对象 */
  FLOW_DOWNLOAD_DATA_BEAN("flow_down_data_bean"),

  /** 下载的网页内容信息的字符数组 */
  FLOW_DOWNLOAD_CONTEXT_ARRAY("flow_download_context_array"),

  /** 链接集合对象信息 */
  FLOW_CONTEXT_HREF_LIST("flow_context_href_list"),


  /**
   * 网页内容分析后的数据
   */
  FLOW_CONTEXT_PROCESS_DATA("flow_context_process_data"),


  ;

  private String key;

  FlowKeyEnum(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
