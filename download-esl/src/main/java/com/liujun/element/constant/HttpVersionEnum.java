package com.liujun.element.constant;

/**
 * @author liujun
 * @version 0.0.1
 * @date 2019/04/05
 */
public enum HttpVersionEnum {
  /** 未加密的http协议l */
  HTTP("http"),

  /** 带加密的http协议 */
  HTTPS("https");

  /** 版本 */
  private String version;

  HttpVersionEnum(String version) {
    this.version = version;
  }

  public String getVersion() {
    return version;
  }
}
